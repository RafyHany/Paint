<template>
  <body @mousemove="fitStageIntoParentContainer">

      <header id="header" >
          <div class="draw">
                  <button id="save">Save</button>
                  <button id="load">Load</button>
                  <div class="inputs">
                      <input type="text" id="name" class="name" placeholder="Enter name of file " style="display: inline;">
                      <input type="text" id="save" class="save" placeholder="File path to save (xml / json)">
                      <input type="text" id="load" class="load" placeholder="File path to load (xml / json)">
                  </div>
                  <div class="format">
                      <ul id="extension">
                          <li id="xml">
                              <input type="radio" name="radio" value="xml">xml</li>
                          <li id="json">
                              <input type="radio" name="radio" value="json"> json</li>
                      </ul>
                      </div>
          </div>
      </header>

      <div class="main-container">
          <div class="sidenavshape" ref="sidenavshape">
              <p class="title" id="shape">Shapes</p>
              <ul class="buttonshape">
                  <li>
                      <img class="images" src="./assets/square.png" alt="">
                      <button class="shapes" id="square" @click="shapeVariable = 1">Square</button>
                  </li>
                  <li>
                      <img class="images" src="./assets/rectangle.png" alt="">
                      <button class="shapes" id="rectangle" @click="shapeVariable = 2">Rectangle</button>
                  </li>
                  <li>
                      <img class="images" src="./assets/triangle.svg" alt="">
                      <button class="shapes" id="triangle" @click="shapeVariable = 4">Triangle</button>
                  </li>
                  <li>
                      <img class="images" src="./assets/circle.svg" alt="">
                      <button class="shapes" id="circle" @click="shapeVariable = 5">Circle</button>
                  </li>
                  
                  <li>
                      <img class="images" src="./assets/ellipse.png" alt="">
                      <button class="shapes" id="ellipse" @click="shapeVariable = 3">Ellipse</button>
                  </li>
                  
                  <li>
                      <img class="images" src="./assets/line.png" alt="">
                      <button class="shapes" id="line" @click="shapeVariable = 6">Line</button>
                  </li>
                  <li>
                      <img class="images" src="./assets/pentagon.png" alt="">
                      <button class="shapes" id="pentagon" @click="shapeVariable = 7">Pentagon</button>
                  </li>
                  <li>
                      <img class="images" src="./assets/hexagon.png" alt="">
                      <button class="shapes" id="hexagon" @click="shapeVariable = 8">Hexagon</button>
                  </li>
                  
              </ul>
          </div>
      
          <section>
              <div id="container" @click="createElement" >
                <v-stage ref="stage" :config="stageConfig" @mousedown="handleStageMouseDown" @touchstart="handleStageMouseDown">
                <v-layer ref="myLayer">
                    <v-circle v-for="shapeConfig in circles" :key="shapeConfig" class="shapeconfig"
                    :config="shapeConfig"></v-circle>
                    <v-rect v-for="shapeConfig in rectangles" :key="shapeConfig" class="shapeconfig"
                    :config="shapeConfig" @transformend="handleTransformEnd"></v-rect>
                    
                    <v-ellipse v-for="shapeConfig in ellipses" :key="shapeConfig" class="shapeconfig"
                    :config="shapeConfig" ></v-ellipse>
                    <v-line v-for="shapeConfig in lines" :key="shapeConfig" class="shapeconfig"
                    :config="shapeConfig"></v-line>
                    <v-regular-polygon v-for="shapeConfig in triangles" :key="shapeConfig" class="shapeconfig"
                    :config="shapeConfig"></v-regular-polygon>
                    <v-regular-polygon v-for="shapeConfig in pentagons" :key="shapeConfig" class="shapeconfig"
                    :config="shapeConfig" ></v-regular-polygon>
                    <v-regular-polygon v-for="shapeConfig in hexagons" :key="shapeConfig" class="shapeconfig"
                    :config="shapeConfig"></v-regular-polygon>
                    <v-transformer ref="transformer" />
                </v-layer>
                </v-stage>
              </div>
          </section>
      
          <div class="sidenavoption">
              <p class="title" id="option">Options</p>
              <button class="options" id="undo">Undo</button>
              <button class="options" id="redo">Redo</button>
              <button class="options" id="erase">Erase</button>
              <button class="options" id="copy">Copy</button>
              <button class="options" id="clear" @click="clear">Clear</button>
              <form>
                  <label id="selectcolor" for="coloring">Select Color</label>
                  <input type="color" id="color" value="#dabf81" >
              </form>
          </div>
      </div>
  </body>
</template>

<script>
import Konva from 'konva';
import { shapes } from 'konva/lib/Shape';
import { Stage, Layer, Rect , Transformer} from 'vue-konva';
import { ref } from 'vue';


const myLayer = ref(null);
export default {
  components: {
    vStage: Stage,
    vLayer: Layer,
    vRect: Rect,
    shapes,
    vTransformer: Transformer,
    shapes
  },

  data(){
    return {
    id: 0,
    rectangles: [],
    circles: [],
    ellipses: [],
    triangles: [],
    pentagons: [],
    hexagons: [],
    lines: [],
    allshapes: [],
    layerConfig: {},
    selectedShapeId: -1,

    sceneWidth: 1000, //any value as it will change instantly to fit the canvas container
    sceneHeight: 1000,

    stageConfig: {
        width: 1000,
        height: 600,
    },
    shapeVariable: 0,
    
    }
  },
  created() {
    window.addEventListener("resize", this.fitStageIntoParentContainer)
  },
  methods:{
    handleTransformEnd(e) {
      // shape is transformed, let us save new attrs back to the node
      // find element in our state
      const rect = this.rectangles.find(
        (r) => r.name === this.selectedShapeName
      );
      console.log(this.selectedShapeName)
      console.log(rect)
      // update the state

      rect.x = e.target.x();
      rect.y = e.target.y();
      rect.rotation = e.target.rotation();
      rect.scaleX = e.target.scaleX();
      rect.scaleY = e.target.scaleY();


    // var rect = ({
        // x: e.clientX-offsetWidthCanvas-100,
        // y: e.clientY-offsetHeightCanvas-50,
        // width: 200,
        // height: 100,
        // fill: color,
        // stroke: 'black',
        // draggable: true,
        // strokeWidth: 3,
        // name: 'rectangle',
        // id: rectangle.id,
        // rotation: e.target.rotation()
    // });


    },
    handleStageMouseDown(e) {
      // clicked on stage - clear selection
      if (e.target === e.target.getStage()) {
        this.selectedShapeId = -1;
        this.updateTransformer();
        return;
      }

      // clicked on transformer - do nothing
      const clickedOnTransformer =
        e.target.getParent().className === 'Transformer';
      if (clickedOnTransformer) {
        return;
      }

      // find clicked rect by its name
      const id = e.target.id();
      const shape = this.allshapes.find((r) => r.id === id);
      if (shape) {
        this.selectedShapeId = Number(id);
      } else {
        this.selectedShapeId = -1;
      }
      this.updateTransformer();
    },
    updateTransformer() {
      // here we need to manually attach or detach Transformer node
      const transformerNode = this.$refs.transformer.getNode();
      const stage = transformerNode.getStage();
      const { selectedShapeId } = this;

      const selectedNode = stage.findOne('#' + String(selectedShapeId));
      console.log(selectedNode)
      // do nothing if selected node is already attached
      if (selectedNode === transformerNode.node()) {
        return;
      }

      if (selectedNode) {
        // attach to another node
        transformerNode.nodes([selectedNode]);
      } else {
        // remove transformer
        transformerNode.nodes([]);
      }
    },



    clear (){
        this.allshapes = []
        this.rectangles= []
        this.circles= []
        this.ellipses= []
        this.triangles= []
        this.pentagons= []
        this.hexagons= []
        this.lines= []
    },
    fitStageIntoParentContainer() {
    var container = window.getComputedStyle(document.getElementById('container'))

    // now we need to fit stage into parent container
    var containerWidth = parseInt(container.width)
    var containerHeight = parseInt(container.height)
    this.stageConfig.width= containerWidth
    this.stageConfig.height= containerHeight
    },

    createElement(event){
        var color = document.getElementById("color").value;

        //for adjusting position of shapes
        var item1ForWidth  = document.querySelector('.sidenavshape').offsetWidth
        var item2ForWidth  = parseInt(window.getComputedStyle(document.getElementById('container')).marginLeft)
        var item1ForHeight = document.querySelector('header').offsetHeight
        var item2ForHeight = parseInt(window.getComputedStyle(document.getElementById('container')).marginLeft)
        var offsetHeightCanvas = item1ForHeight + item2ForHeight
        var offsetWidthCanvas  = item1ForWidth + item2ForWidth

        switch (this.shapeVariable){
            case 1:
                var square = ({
                    x: event.clientX-offsetWidthCanvas-50,
                    y: event.clientY-offsetHeightCanvas-50,
                    width: 100,
                    height: 100,
                    fill: color,
                    stroke: 'black',
                    strokeWidth: 3,
                    draggable: true,
                    name: 'square',
                    id: String(this.id)
                });
                this.id += 1
                this.rectangles.push(square)
                this.allshapes.push(square)
                break;
            case 2: 
                var rect = ({
                    x: event.clientX-offsetWidthCanvas-100,
                    y: event.clientY-offsetHeightCanvas-50,
                    width: 200,
                    height: 100,
                    fill: color,
                    stroke: 'black',
                    draggable: true,
                    strokeWidth: 3,
                    name: 'rectangle',
                    id: String(this.id)
                });
                this.id += 1
                this.rectangles.push(rect)
                this.allshapes.push(rect)
                break;
            case 3: 
                var ellipse = ({
                    x: event.clientX-offsetWidthCanvas,
                    y: event.clientY-offsetHeightCanvas,
                    radiusX: 100,
                    radiusY: 50,
                    fill: color,
                    stroke: 'black',
                    draggable: true,
                    strokeWidth: 3,
                    name: 'ellipse',
                    id: String(this.id)
                });
                this.id += 1
                this.ellipses.push(ellipse)
                this.allshapes.push(ellipse)
                break;
            case 4: 
                var triangle = ({
                    x: event.clientX-offsetWidthCanvas,
                    y: event.clientY-offsetHeightCanvas,
                    sides: 3,
                    radius: 100,
                    fill: color,
                    stroke: 'black',
                    strokeWidth: 3,
                    name: 'triangle',
                    id: String(this.id)
                });
                this.id += 1
                this.triangles.push(triangle)
                this.allshapes.push(triangle)
                break;
            case 5: 
                var circle = ({
                    x: event.clientX-offsetWidthCanvas,
                    y: event.clientY-offsetHeightCanvas,
                    radius: 60,
                    fill: color,
                    stroke: 'black',
                    strokeWidth: 3,
                    name: 'circle',
                    id: String(this.id)
                });
                this.id +=1
                this.circles.push(circle)
                this.allshapes.push(circle)
                break;
            case 6: 
                var line = ({
                    points: [event.clientX-100-offsetWidthCanvas, event.clientY-100-offsetHeightCanvas,
                            event.clientX+100-offsetWidthCanvas, event.clientY+100-offsetHeightCanvas],
                    stroke: color,
                    strokeWidth: 10,
                    lineCap: 'round',
                    lineJoin: 'round',
                    name: 'line',
                    id: String(this.id)
                });
                this.id +=1
                this.lines.push(line)
                this.allshapes.push(line)
                break;
            case 7:
                var pentagon = ({
                    x: event.clientX-offsetWidthCanvas,
                    y: event.clientY-offsetHeightCanvas,
                    sides: 5,
                    radius: 70,
                    fill: color,
                    stroke: 'black',
                    strokeWidth: 3,
                    name: 'pentagon',
                    id: String(this.id)
                });
                this.id += 1
                this.pentagons.push(pentagon)
                this.allshapes.push(pentagon)
                break;
            case 8:
                var hexagon = ({
                    x: event.clientX-offsetWidthCanvas,
                    y: event.clientY-offsetHeightCanvas,
                    sides: 6,
                    radius: 70,
                    fill: color,
                    stroke: 'black',
                    strokeWidth: 3,
                    name: 'hexagon',
                    id: String(this.id)
                });
                this.id +=1
                this.hexagons.push(hexagon)
                this.allshapes.push(hexagon)
                break;
        }
        console.log(this.allshapes)
        this.shapeVariable = 0
    },

    // transform (){
    // //selection color -> 0 102 204
    //     var tr = new Konva.Transformer();

    //     // add a new feature, lets add ability to draw selection rectangle
    //     var selectionRectangle = new Konva.Rect({
    //         fill: 'rgba(0,102,204,0.5)',
    //         visible: false,
    //     });
    //     layer.add(selectionRectangle);

    //     var x1, y1, x2, y2;
    //     stage.on('mousedown touchstart', (e) => {
    //         // do nothing if we mousedown on any shape
    //         if (e.target !== stage) {
    //         return;
    //         }
    //         e.evt.preventDefault();
    //         x1 = stage.getPointerPosition().x;
    //         y1 = stage.getPointerPosition().y;
    //         x2 = stage.getPointerPosition().x;
    //         y2 = stage.getPointerPosition().y;

    //         selectionRectangle.visible(true);
    //         selectionRectangle.width(0);
    //         selectionRectangle.height(0);
    //     });

    //     stage.on('mousemove touchmove', (e) => {
    //         // do nothing if we didn't start selection
    //         if (!selectionRectangle.visible()) {
    //         return;
    //         }
    //         e.evt.preventDefault();
    //         x2 = stage.getPointerPosition().x;
    //         y2 = stage.getPointerPosition().y;
    //         selectionRectangle.setAttrs({
    //         x: Math.min(x1, x2),
    //         y: Math.min(y1, y2),
    //         width: Math.abs(x2 - x1),
    //         height: Math.abs(y2 - y1),
    //         });
    //     });

    //     stage.on('mouseup touchend', (e) => {
    //         // do nothing if we didn't start selection
    //         if (!selectionRectangle.visible()) {
    //         return;
    //         }
    //         e.evt.preventDefault();
    //         // update visibility in timeout, so we can check it in click event
    //         setTimeout(() => {
    //         selectionRectangle.visible(false);
    //         });

    //         var shapes = stage.find('.rectangle');          //!!!!!!!!!!!!!!!!!!!!!!!!!
    //         var box = selectionRectangle.getClientRect();
    //         var selected = shapes.filter((shape) =>
    //         Konva.Util.haveIntersection(box, shape.getClientRect())
    //         );
    //         tr.nodes(selected);
    //     });

    //     // clicks should select/deselect shapes
    //     stage.on('click tap', function (e) {
    //         // if we are selecting with rect, do nothing
    //         if (selectionRectangle.visible()) {
    //         return;
    //         }

    //         // if click on empty area - remove all selections
    //         if (e.target === stage) {
    //         tr.nodes([]);
    //         return;
    //         }

    //         // do nothing if clicked NOT on our rectangles
    //         if (!e.target.hasName('rectangle')) {
    //         return;
    //         }

    //         // do we pressed shift or ctrl?
    //         const metaPressed = e.evt.shiftKey || e.evt.ctrlKey || e.evt.metaKey;
    //         const isSelected = tr.nodes().indexOf(e.target) >= 0;

    //         if (!metaPressed && !isSelected) {
    //         // if no key pressed and the node is not selected
    //         // select just one
    //         tr.nodes([e.target]);
    //         } else if (metaPressed && isSelected) {
    //         // if we pressed keys and node was selected
    //         // we need to remove it from selection:
    //         const nodes = tr.nodes().slice(); // use slice to have new copy of array
    //         // remove node from array
    //         nodes.splice(nodes.indexOf(e.target), 1);
    //         tr.nodes(nodes);
    //         } else if (metaPressed && !isSelected) {
    //         // add the node into selection
    //         const nodes = tr.nodes().concat([e.target]);
    //         tr.nodes(nodes);
    //         }
    //         tr.zIndex(100) //for the dialog box to be on top - comment this line and see the difference !
    //     });
    // }
  }
}








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
</script>

<style>
body, p { margin:0; padding:0}


html {
    height: 100%;
}

body{
    height:100%;
    align-items: center;
    justify-content: center;
    background: linear-gradient(to right,#e8d8b2,#dabf81 );
}

.main-container {
    display: flex;
    width: 100%;
    justify-content: space-between;
}

section {
    width: 70%;
    height: calc(900px * 0.6);
}

#container {
    height: 100%;
    background-color: #fefefe;
    margin: calc(15px * 0.6);
    border-radius: calc(50px * 0.6);
}

header{
    width: calc(900px * 0.6);
    height: calc(110px * 0.6);
    align-items: center;
    padding: calc(16px * 0.6);
    color: white;
    border-radius: calc(50px * 0.6);
    background: linear-gradient(to top,#92399a 0%,#bd82be );
    margin: auto;
    border: #770881;

}

.draw{
    margin-left: calc(100px * 0.6);
}

#save, #load{
    width: calc(120px * 0.6);
    border-radius: calc(30px * 0.6);
    padding : calc(8px * 0.6) calc(10px * 0.6);
    margin-bottom: calc(4px * 0.6);
    margin-top: calc(4px * 0.6);
    font-weight: 900;
    font-size: calc(33px * 0.6);
    color: black;
    text-align: center;
    font-family: "Times New Roman", Arial, Helvetica, sans-serif;
    cursor: pointer;
    background: rgb(243, 209, 255);
    margin-left: calc(3px * 0.6);
    align-content: center;
    align-items: center;
    justify-content: center;  
}

#load{
    margin-left: calc(390px * 0.6);
}

#save:hover, #load:hover{
    background-color: #664209;
    color: rgb(243, 290, 255);
}

.title{
    margin-top: calc(15px * 0.6);
    align-content: center;
    align-items: center;
    justify-content: center;
    padding: calc(8px * 0.6) calc(40px * 0.6);
    font-weight: 1000;
    font-size: calc(35px * 0.6);
    color: #664209;
    text-align: center;
    font-family:  Helvetica, sans-serif;
    display:flex;
    width: calc(60px * 0.6);
    border-radius: calc(10px * 0.6);
}

.images{
    margin-right: calc(5px * 0.6);
    filter:drop-shadow(100%) contrast(100%) saturate(3000%);
    margin-left: calc(-10px * 0.6);
}

.shapes, .options{
    width: 100%;
    padding : calc(5px * 0.6) 0;
    margin-bottom: calc(5px * 0.6);
    margin-top: calc(4px * 0.6);
    border-radius: calc(15px * 0.6);
    color:rgb(2,0,2);
    font-size: calc(20px * 0.6);
    cursor: pointer;
    background: rgb(243, 209, 255);
    font-family: 'Signika Negative', sans-serif;
}

.options{
    width: 80%;
}

form {
    display: flex;
    flex-direction: column;
    align-items: center;
}

#selectcolor{
    width: 100%;
    margin: calc(4px * 0.6) 0px calc(4px * 0.6) calc(25px * 0.6);
    padding : calc(8px * 0.6) 0;
    color:rgb(2,0,2);
    font-size: calc(22px * 0.6);
    font-weight: bold;
    font-style: italic;
    cursor: pointer;
    font-family: 'Signika Negative', sans-serif;
}

#color{
    height: calc(50px * 0.6);
    width: calc(50px * 0.6);
    background-color: transparent;
    border: inset;
    border-radius: 100%;
    cursor: pointer;
}

.shapes:hover, .options:hover{
    background-color: #664209;
    color: rgb(243, 290, 255);
}

.sidenavoption {
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 10%;
    padding:calc(5px * 0.6) calc(30px * 0.6) calc(20px * 0.6) calc(30px * 0.6);
    height:calc(600px * 0.6);
    margin: calc(15px * 0.6);
    background: linear-gradient(to right,#92399a 0%,#bd82be );
    padding-top: calc(20px * 0.6);
    border-radius: calc(50px * 0.6);
}

.sidenavoption button {
    padding: calc(6px * 0.6) calc(16px * 0.6);
    text-decoration: none;
    font-size: calc(25px * 0.6);
    color: black;
    display: block;
}

.sidenavoption button:hover {
    color: #f1f1f1;
}
.sidenavshape {
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 10%;
    padding:calc(5px * 0.6) calc(30px * 0.6) calc(20px * 0.6);
    height: calc(600px * 0.6);
    margin: calc(15px * 0.6);
    background: linear-gradient(to left,#92399a 0%,#bd82be );
    padding-top: calc(20px * 0.6);
    border-radius: calc(50px * 0.6);
}

.sidenavshape button {
    padding: calc(6px * 0.6) calc(16px * 0.6);
    text-decoration: none;
    font-size: calc(25px * 0.6);
    color: black;
    display: block;
    text-align: center;
    align-items: center;
    justify-items: center;
}

.sidenavshape button:hover {
    color: #f1f1f1;
}
ul{
    padding-left: calc(5px * 0.6);
    margin: calc(2px * 0.6) 0px 0px calc(5px * 0.6);
}

li{
    display: flex;
    cursor: pointer;
    align-items: center;
}

li #xml, li #json{
    display: inline;
}

.inputs{
    display: inline-flex;
}

.save{
    border-radius: calc(10px * 0.6);
    text-align:left;
    justify-content: center;
    padding: calc(5px * 0.6);
    padding-right:calc(2px * 0.6);
    border-color: #010811;
    border-width: calc(2px * 0.6);
    margin-left:calc(5px * 0.6);
    font-size:calc(15px * 0.6);
    width: 33%;
    
}

.load{
    border-radius: calc(10px * 0.6);
    text-align:left;
    justify-content: center;
    padding: calc(5px * 0.6);
    padding-right:calc(2px * 0.6);
    border-color: #010811;
    border-width: calc(2px * 0.6);
    margin-left:calc(150px * 0.6);
    font-size:calc(15px * 0.6);
    width: 33%;
}
.name{
    border-radius: calc(10px * 0.6);
    text-align:left;
    justify-content: center;
    padding: calc(5px * 0.6);
    padding-right:calc(2px * 0.6);
    border-color: #010811;
    border-width: calc(2px * 0.6);
    font-size:calc(15px * 0.6);
    width: calc(25% * 0.6);
} 

input::-webkit-color-swatch{
    border-radius: calc(20px * 0.6);
    border: calc(4px * 0.6) solid black;
}

.format {
    align-items: center;
    justify-content: center;
    margin-left:calc(15px * 0.6) ;
}

.format  #json, .format #xml{
    font-size: calc(20px * 0.6);
}

.format input{
    accent-color:  rgb(75, 23, 95);
}

#extension{
    display: inline-flex;
    margin-left:calc(70px * 0.6);
}
</style>
