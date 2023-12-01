var range = document.getElementById("range");
var rangeValue = document.getElementById("range-value");
rangeValue.innerHTML = range.value;

range.oninput = function() {
    rangeValue.innerHTML = this.value;
}
// variable stroke width have problems with transform (scaling) box

//problem here : need to be responsive 
var stage = new Konva.Stage({
    container: 'container',
    width: window.innerWidth,
    height: 400,
});

var layer = new Konva.Layer();
stage.add(layer);

var shapeVariable = 0



document.getElementById('square').addEventListener('click', function (){shapeVariable = 1});
document.getElementById('rectangle').addEventListener('click', function (){shapeVariable = 2});
document.getElementById('ellipse').addEventListener('click', function (){shapeVariable = 3});
document.getElementById('triangle').addEventListener('click', function (){shapeVariable = 4});
document.getElementById('circle').addEventListener('click', function (){shapeVariable = 5});
document.getElementById('line').addEventListener('click', function (){shapeVariable = 6});
document.getElementById('pentagon').addEventListener('click', function (){shapeVariable = 7});
document.getElementById('hexagon').addEventListener('click', function (){shapeVariable = 8});


document.getElementById('container').addEventListener('click', createElement);
function createElement(event){
    var color = document.getElementById("color").value; 

    switch (shapeVariable){
        case 1:
            var square = new Konva.Rect({
                x: event.clientX-50,
                y: event.clientY-250, //-(height of header + 50)
                width: 100,
                height: 100,
                fill: color,
                stroke: 'black',
                strokeWidth: 3,
                draggable: true
            });
            square['attrs']['name'] = 'square';
            layer.add(square);
            break;
        case 2:
            var rect = new Konva.Rect({
                x: event.clientX-100,
                y: event.clientY-250,
                width: 200,
                height: 100,
                fill: color,
                stroke: 'black',
                draggable: true,
                strokeWidth: 3,
            });
            rect['attrs']['name'] = 'rectangle';
            layer.add(rect);
            break;
        case 3: 
            var ellipse = new Konva.Ellipse({
                x: event.clientX,
                y: event.clientY-200,
                radiusX: 100,
                radiusY: 50,
                fill: color,
                stroke: 'black',
                draggable: true,
                strokeWidth: 3,
            });
            ellipse['attrs']['name'] = 'ellipse';
            layer.add(ellipse);
            break;
        case 4: 
            var triangle = new Konva.RegularPolygon({
                x: event.clientX,
                y: event.clientY-200,
                sides: 3,
                radius: 100,
                fill: color,
                stroke: 'black',
                strokeWidth: 3,
            });
            triangle['attrs']['name'] = 'triangle';
            layer.add(triangle)
            break;
        case 5: 
            var circle = new Konva.Circle({
                x: event.clientX,
                y: event.clientY-200,
                radius: 60,
                fill: color,
                stroke: 'black',
                strokeWidth: 3,
            });
            circle['attrs']['name'] = 'circle';
            layer.add(circle);
            break;
        case 6: 
            var line = new Konva.Line({
                points: [event.clientX-100, event.clientY-300, event.clientX+100, event.clientY-100],
                stroke: color,
                strokeWidth: 10,
                lineCap: 'round',
                lineJoin: 'round',
            });
            line['attrs']['name'] = 'line';
            layer.add(line);
            break;
        case 7:
            var pentagon = new Konva.RegularPolygon({
                x: event.clientX,
                y: event.clientY-200,
                sides: 5,
                radius: 70,
                fill: color,
                stroke: 'black',
                strokeWidth: 3,
            });
            pentagon['attrs']['name'] = 'pentagon';
            layer.add(pentagon);
            break;
        case 8:
            var hexagon = new Konva.RegularPolygon({
                x: event.clientX,
                y: event.clientY-200,
                sides: 6,
                radius: 70,
                fill: color,
                stroke: 'black',
                strokeWidth: 3,
            });
            hexagon['attrs']['name'] = 'hexagon';
            layer.add(hexagon);
            break;

    }
    shapeVariable = 0
}

//clear 
document.getElementById('clear').addEventListener('click',function (){layer.destroyChildren() });




//transform

//selection color -> 0 102 204
var tr = new Konva.Transformer();
    layer.add(tr);

      // add a new feature, lets add ability to draw selection rectangle
    var selectionRectangle = new Konva.Rect({
        fill: 'rgba(0,102,204,0.5)',
        visible: false,
    });
    layer.add(selectionRectangle);

    var x1, y1, x2, y2;
    stage.on('mousedown touchstart', (e) => {
        // do nothing if we mousedown on any shape
        if (e.target !== stage) {
        return;
        }
        e.evt.preventDefault();
        x1 = stage.getPointerPosition().x;
        y1 = stage.getPointerPosition().y;
        x2 = stage.getPointerPosition().x;
        y2 = stage.getPointerPosition().y;

        selectionRectangle.visible(true);
        selectionRectangle.width(0);
        selectionRectangle.height(0);
    });

    stage.on('mousemove touchmove', (e) => {
        // do nothing if we didn't start selection
        if (!selectionRectangle.visible()) {
        return;
        }
        e.evt.preventDefault();
        x2 = stage.getPointerPosition().x;
        y2 = stage.getPointerPosition().y;
        selectionRectangle.setAttrs({
        x: Math.min(x1, x2),
        y: Math.min(y1, y2),
        width: Math.abs(x2 - x1),
        height: Math.abs(y2 - y1),
        });
    });

    stage.on('mouseup touchend', (e) => {
        // do nothing if we didn't start selection
        if (!selectionRectangle.visible()) {
        return;
        }
        e.evt.preventDefault();
        // update visibility in timeout, so we can check it in click event
        setTimeout(() => {
        selectionRectangle.visible(false);
        });

        var shapes = stage.find('.rectangle');          //!!!!!!!!!!!!!!!!!!!!!!!!!
        var box = selectionRectangle.getClientRect();
        var selected = shapes.filter((shape) =>
        Konva.Util.haveIntersection(box, shape.getClientRect())
        );
        tr.nodes(selected);
    });

      // clicks should select/deselect shapes
    stage.on('click tap', function (e) {
        // if we are selecting with rect, do nothing
        if (selectionRectangle.visible()) {
        return;
        }

        // if click on empty area - remove all selections
        if (e.target === stage) {
        tr.nodes([]);
        return;
        }

        // do nothing if clicked NOT on our rectangles
        if (!e.target.hasName('rectangle')) {
        return;
        }

        // do we pressed shift or ctrl?
        const metaPressed = e.evt.shiftKey || e.evt.ctrlKey || e.evt.metaKey;
        const isSelected = tr.nodes().indexOf(e.target) >= 0;

        if (!metaPressed && !isSelected) {
          // if no key pressed and the node is not selected
          // select just one
        tr.nodes([e.target]);
        } else if (metaPressed && isSelected) {
          // if we pressed keys and node was selected
          // we need to remove it from selection:
          const nodes = tr.nodes().slice(); // use slice to have new copy of array
          // remove node from array
        nodes.splice(nodes.indexOf(e.target), 1);
        tr.nodes(nodes);
        } else if (metaPressed && !isSelected) {
          // add the node into selection
        const nodes = tr.nodes().concat([e.target]);
        tr.nodes(nodes);
        }
        tr.zIndex(100) //for the dialog box to be on top - comment this line and see the difference !
    });

    // ... (Your existing code)

// Function to add the current state to the undo stack
function addToUndoStack() {
  var clonedState = layer.getChildren().toArray().map(shape => shape.clone());
  appHistory.push(clonedState);
  appHistoryStep = appHistory.length - 1;
}

// Function to undo the last action
function undoAction() {
  if (appHistoryStep > 0) {
    appHistoryStep--;
    var prevState = appHistory[appHistoryStep];
    layer.destroyChildren();
    layer.add(...prevState);
    layer.draw();
  }
}

// Function to redo the last undone action
function redoAction() {
  if (appHistoryStep < appHistory.length - 1) {
    appHistoryStep++;
    var nextState = appHistory[appHistoryStep];
    layer.destroyChildren();
    layer.add(...nextState);
    layer.draw();
  }
}

// Event listeners for undo and redo buttons
document.getElementById('undo').addEventListener('click', undoAction);
document.getElementById('redo').addEventListener('click', redoAction);

// ... (Your existing code)





// document.getElementById('brush').addEventListener('click', brushTool);

// function brushTool(event){
//     var brush = new Konva.Line({
//         points: [],
//         stroke: 'black',
//         strokeWidth: 5,
//         lineJoin: 'round',
//         lineCap: 'round'
//     });

//     // Add the brush to the layer
    

//     // Variable to track whether the user is drawing
//     var isDrawing = false;

//     // Function to start drawing
//     function startDrawing() {
//         isDrawing = true;
//         // Get the current pointer position
//         var pos = stage.getPointerPosition();
//         // Set the starting point of the brush
//         brush.points([pos.x, pos.y]);
//         layer.batchDraw();
//     }

//     // Function to draw as the pointer moves
//     function draw() {
//         if (!isDrawing) {
//             return;
//         }
//         // Get the current pointer position
//         var pos = stage.getPointerPosition();
//         // Add the current point to the brush's points
//         var oldPoints = brush.points();
//         oldPoints = oldPoints.concat([pos.x, pos.y]);
//         brush.points(oldPoints);
//         layer.batchDraw();
//     }

//     // Function to stop drawing
//     function stopDrawing() {
//         isDrawing = false;
//     }

//     layer.add(brush);

//     // Event listeners for drawing
//     stage.on('mousedown touchstart', startDrawing);
//     stage.on('mousemove touchmove', draw);
//     stage.on('mouseup touchend', stopDrawing);
// }




    




