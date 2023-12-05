<template>
    <body @mousemove="fitStageIntoParentContainer">
      <div class="control-header">
        <img class="imagesmain2" src="./assets/background.png" alt="">
        <header id="header" >
              
            <div class="draw">
              <div class="save-load">
                   <img class="images" src="./assets/save.png" alt="">
                    <button id="save" @click="save">Save</button>
                      <button id="load" @click="load" style="margin-right: 10px;">Load</button>
                       <img class="images" src="./assets/load.png" alt="">
              </div>
  
                    <div class="inputs">
                        <input type="text" class="name" v-model="name" placeholder="Enter name of file" style="display: inline;">
                        <input type="text" class="save" v-model="path" placeholder="File path to save (xml / json)">
                        <input type="text" class="load" v-model="path" placeholder="File path to load (xml / json)">
                    </div>
                    <div class="format">
                          <ul id="extension">
                              <li id="xml">
                                  <input type="radio" name="radio" v-model="format" value="xml">xml</li>
                              <li id="json">
                                  <input type="radio" name="radio" v-model="format" value="json">json</li>
                          </ul>
                        </div>
            </div>
        </header>
        <img class="imagesmain1" src="./assets/background.png" alt="">
      </div>
  
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
                      :config="shapeConfig" @dragend="handleDragEnd" @transformend="handleTransformEnd"></v-circle>
                      <v-rect v-for="shapeConfig in rectangles" :key="shapeConfig" class="shapeconfig"
                      :config="shapeConfig" @transformend="handleTransformEnd" @dragend="handleDragEnd"></v-rect>
                      
                      <v-ellipse v-for="shapeConfig in ellipses" :key="shapeConfig" class="shapeconfig"
                      :config="shapeConfig" @transformend="handleTransformEnd" @dragend="handleDragEnd"></v-ellipse>
                      <v-line v-for="shapeConfig in lines" :key="shapeConfig" class="shapeconfig"
                      :config="shapeConfig" @transformend="handleTransformEnd" @dragend="handleDragEnd"></v-line>
                      <v-regular-polygon v-for="shapeConfig in triangles" :key="shapeConfig" class="shapeconfig"
                      :config="shapeConfig" @transformend="handleTransformEnd" @dragend="handleDragEnd"></v-regular-polygon>
                      <v-regular-polygon v-for="shapeConfig in pentagons" :key="shapeConfig" class="shapeconfig"
                      :config="shapeConfig" @transformend="handleTransformEnd" @dragend="handleDragEnd"></v-regular-polygon>
                      <v-regular-polygon v-for="shapeConfig in hexagons" :key="shapeConfig" class="shapeconfig"
                      :config="shapeConfig" @transformend="handleTransformEnd" @dragend="handleDragEnd"></v-regular-polygon>
                      <v-transformer ref="transformer" />
                  </v-layer>
                  </v-stage>
                </div>
            </section>
        
            <div class="sidenavoption">
                <p class="title" id="option">Options</p>
                <ul>
                  <li>
                      <img class="images" src="./assets/undo.png" alt="">
                       <button class="options" id="undo"  @click="undo"          >Undo</button>
                  </li>
                  <li>
                      <img class="images" src="./assets/redo.png" alt="">
                      <button class="options" id="redo"  @click="redo"          >Redo</button>
                  </li>
                  
                  <li>
                      <img class="images" src="./assets/eraser.svg" alt="">
                      <button class="options" id="erase" @click="deleteFlag = 1, copyFlag = 0, colorFlag = 0">Erase</button>
                  </li>
                  <li>
                      <img class="images" src="./assets/copy.png" alt="">
                      <button class="options" id="copy"  @click="copyFlag = 1, deleteFlag = 0, colorFlag = 0"  >Copy</button>
                  </li>
                  <li>
                      <img class="images" src="./assets/clear.png" alt="" style="width:14px">
                      <button class="options" id="clear" @click="clear">Clear</button>
                  </li>
                </ul>
                <form>
                    <label id="selectcolor" for="coloring">Select Color</label>
                    <input type="color" id="color" value="#ededed" @click="colorFlag = 1, copyFlag = 0, deleteFlag = 0">
                </form>
            </div>
        </div>
        <footer>
          <img class="imagesmain1" src="./assets/background.png" alt="">
          <img class="imagesmain2" src="./assets/background.png" alt="">
        </footer>
    </body>
  </template>
  
  <script>
  import Konva from 'konva';
  import { isProxy, toRaw } from 'vue';
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
      colorFlag: 0,
      copyFlag: 0,
      deleteFlag: 0,
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
      //handle draging
      handleDragEnd(e){
          console.log(e.target.x())
          console.log(e.target.y())
          console.log(e.target.id())
          var shape = this.allshapes.find((r) => r.id === e.target.id())
          shape.x = e.target.x()
          shape.y = e.target.y()
          if(shape.name === "square"){
              shape = this.rectangles.find((r) => r.id === e.target.id())
              shape.x = e.target.x()
              shape.y = e.target.y()
          }
          else if(shape.name === "rectangle"){
              shape = this.rectangles.find((r) => r.id === e.target.id())
              shape.x = e.target.x()
              shape.y = e.target.y()
          }
          else if(shape.name === "circle"){
              shape = this.circles.find((r) => r.id === e.target.id())
              shape.x = e.target.x()
              shape.y = e.target.y()
          }
          else if(shape.name === "triangle"){
              shape = this.triangles.find((r) => r.id === e.target.id())
              shape.x = e.target.x()
              shape.y = e.target.y()
          }
          else if(shape.name === "pentagon"){
              shape = this.pentagons.find((r) => r.id === e.target.id())
              shape.x = e.target.x()
              shape.y = e.target.y()
          }
          else if(shape.name === "hexagon"){
              shape = this.hexagons.find((r) => r.id === e.target.id())
              shape.x = e.target.x()
              shape.y = e.target.y()
          }
          else if(shape.name === "line"){
              shape = this.lines.find((r) => r.id === e.target.id())
              shape.x = e.target.x()
              shape.y = e.target.y()
          }
          else if(shape.name === "elipse"){
              shape = this.ellipses.find((r) => r.id === e.target.id())
              shape.x = e.target.x()
              shape.y = e.target.y()
          }
          
  
          fetch("http://localhost:8081/paint/update",{
              method:"PUT",
              headers : {
                  'Content-type':'application/json',
              },
              body : JSON.stringify(shape)
              }).then(responce=>{
              return responce.text();
              }).then(data =>{
              console.log(data);
              }).catch(error => {
              console.error("Error:", error);
              this.isError = true;
              });
  
      },
  
  
      //for handling transformation (eg. resizing)
      async handleTransformEnd(e) {
          var shape = this.allshapes.find(
              (r) => r.id === this.selectedShapeId
          );
          shape.x = e.target.x();
          shape.y = e.target.y();
          shape.rotation = e.target.rotation();
          shape.scaleX = e.target.scaleX();
          shape.scaleY = e.target.scaleY();
          
          if(shape.name === "square"){
              shape = this.rectangles.find((r) => r.id === this.selectedShapeId)
              shape.x = e.target.x();
              shape.y = e.target.y();
              shape.rotation = e.target.rotation();
              shape.scaleX = e.target.scaleX();
              shape.scaleY = e.target.scaleY();
          }
          else if(shape.name === "rectangle"){
              shape = this.rectangles.find((r) => r.id === this.selectedShapeId)
              shape.x = e.target.x();
              shape.y = e.target.y();
              shape.rotation = e.target.rotation();
              shape.scaleX = e.target.scaleX();
              shape.scaleY = e.target.scaleY();
          }
          else if(shape.name === "circle"){
              shape = this.circles.find((r) => r.id === this.selectedShapeId)
              shape.x = e.target.x();
              shape.y = e.target.y();
              shape.rotation = e.target.rotation();
              shape.scaleX = e.target.scaleX();
              shape.scaleY = e.target.scaleY();
          }
          else if(shape.name === "triangle"){
              shape = this.triangles.find((r) => r.id === this.selectedShapeId)
              shape.x = e.target.x();
              shape.y = e.target.y();
              shape.rotation = e.target.rotation();
              shape.scaleX = e.target.scaleX();
              shape.scaleY = e.target.scaleY();
          }
          else if(shape.name === "pentagon"){
              shape = this.pentagons.find((r) => r.id === this.selectedShapeId)
              shape.x = e.target.x();
              shape.y = e.target.y();
              shape.rotation = e.target.rotation();
              shape.scaleX = e.target.scaleX();
              shape.scaleY = e.target.scaleY();
          }
          else if(shape.name === "hexagon"){
              shape = this.hexagons.find((r) => r.id === this.selectedShapeId)
              shape.x = e.target.x();
              shape.y = e.target.y();
              shape.rotation = e.target.rotation();
              shape.scaleX = e.target.scaleX();
              shape.scaleY = e.target.scaleY();
          }
          else if(shape.name === "line"){
              shape = this.lines.find((r) => r.id === this.selectedShapeId)
              shape.x = e.target.x();
              shape.y = e.target.y();
              shape.rotation = e.target.rotation();
              shape.scaleX = e.target.scaleX();
              shape.scaleY = e.target.scaleY();
          }
          else if(shape.name === "elipse"){
              shape = this.ellipses.find((r) => r.id === this.selectedShapeId)
              shape.x = e.target.x();
              shape.y = e.target.y();
              shape.rotation = e.target.rotation();
              shape.scaleX = e.target.scaleX();
              shape.scaleY = e.target.scaleY();
          }
          
  
          await fetch("http://localhost:8081/paint/update",{
              method:"PUT",
              headers : {
                  'Content-type':'application/json',
              },
              body : JSON.stringify(shape)
              }).then(responce=>{
              return responce.text();
              }).then(data =>{
              console.log(data);
              }).catch(error => {
              console.error("Error:", error);
              this.isError = true;
              });
      },
      async handleStageMouseDown(e) {
        // clicked on stage - clear selection
        if (e.target === e.target.getStage()) {
          this.deleteFlag = 0
          this.copyFlag = 0
          this.colorFlag = 0
          this.selectedShapeId = -1;
          this.updateTransformer(false);
          return;
        }
        // clicked on transformer - do nothing
        const clickedOnTransformer =
          e.target.getParent().className === 'Transformer';
        if (clickedOnTransformer) {
          this.deleteFlag = 0
          this.copyFlag = 0
          this.colorFlag = 0
          return;
        }
  
        // find clicked shape by its name
        const id = e.target.id();
        var shape = this.allshapes.find((r) => r.id === id);
        if (shape) {
          if(this.deleteFlag){
          this.rectangles= []
          this.circles= []
          this.ellipses= []
          this.triangles= []
          this.pentagons= []
          this.hexagons= []
          this.lines= []
          await fetch("http://localhost:8081/paint/remove/"+ String(id),{
              method:"DELETE",
              headers : {
                  'Content-type':'application/json',
              },
              body : JSON.stringify(shape)
              }).then(responce=>{
              return responce.text();
              }).then(data =>{
              this.allshapes = JSON.parse(data);
              }).catch(error => {
              console.error("Error:", error);
              this.isError = true;
              });
          this.allshapes.forEach((arrayItem) => {
              if(arrayItem.name === "square")
                  this.rectangles.push(arrayItem)
              else if(arrayItem.name === "rectangle")
                  this.rectangles.push(arrayItem)
              else if(arrayItem.name === "triangle")
                  this.triangles.push(arrayItem)
              else if(arrayItem.name === "elipse")
                  this.ellipses.push(arrayItem)
              else if(arrayItem.name === "line")
                  this.lines.push(arrayItem)
              else if(arrayItem.name === "pentagon")
                  this.pentagons.push(arrayItem)
              else if(arrayItem.name === "hexagon")
                  this.hexagons.push(arrayItem)
              else if(arrayItem.name === "circle")
                  this.circles.push(arrayItem)
          });
          this.deleteFlag = 0
          this.updateTransformer(true)
          return
          }
  
          else if(this.copyFlag){
          var copiedShape = 0
          await fetch("http://localhost:8081/paint/clone/"+ String(id) + "/" + String(this.id),{
              method:"POST",
              headers : {
                  'Content-type':'application/json',
              },
              body : JSON.stringify(shape)
              }).then(responce=>{
              return responce.text();
              }).then(data =>{
              copiedShape = JSON.parse(data)
              }).catch(error => {
              console.error("Error:", error);
              this.isError = true;
              });
          this.allshapes.push(copiedShape)
              if(copiedShape.name === "square")
                  this.rectangles.push(copiedShape)
              else if(copiedShape.name === "rectangle")
                  this.rectangles.push(copiedShape)
              else if(copiedShape.name === "triangle")
                  this.triangles.push(copiedShape)
              else if(copiedShape.name === "elipse")
                  this.ellipses.push(copiedShape)
              else if(copiedShape.name === "line")
                  this.lines.push(copiedShape)
              else if(copiedShape.name === "pentagon")
                  this.pentagons.push(copiedShape)
              else if(copiedShape.name === "hexagon")
                  this.hexagons.push(copiedShape)
              else if(copiedShape.name === "circle")
                  this.circles.push(copiedShape)
          this.copyFlag = 0
          this.updateTransformer(true)
          this.id = String((Number(this.id) + 1));
          return
          }
  
          else if(this.colorFlag){
          var color = document.getElementById("color").value;
  
          
          if(shape.name === "square"){
              shape.fill = color
              shape = this.rectangles.find((r) => r.id === id)
              shape.fill = color
          }
          else if(shape.name === "rectangle"){
              shape.fill = color
              shape = this.rectangles.find((r) => r.id === id)
              shape.fill = color
          }
          else if(shape.name === "circle"){
              shape.fill = color
              shape = this.circles.find((r) => r.id === id)
              shape.fill = color
          }
          else if(shape.name === "triangle"){
              shape.fill = color
              shape = this.triangles.find((r) => r.id === id)
              shape.fill = color
          }
          else if(shape.name === "pentagon"){
              shape.fill = color
              shape = this.pentagons.find((r) => r.id === id)
              shape.fill = color
          }
          else if(shape.name === "hexagon"){
              shape.fill = color
              shape = this.hexagons.find((r) => r.id === id)
              shape.fill = color
          }
          else if(shape.name === "line"){
              shape.stroke = color
              shape = this.lines.find((r) => r.id === id)
              shape.stroke = color
          }
          else if(shape.name === "elipse"){
              shape.fill = color
              shape = this.ellipses.find((r) => r.id === id)
              shape.fill = color
          }
  
          await fetch("http://localhost:8081/paint/update",{
              method:"PUT",
              headers : {
                  'Content-type':'application/json',
              },
              body : JSON.stringify(shape)
              }).then(responce=>{
              return responce.text();
              }).then(data =>{
              console.log(data);
              }).catch(error => {
              console.error("Error:", error);
              this.isError = true;
              });
          this.colorFlag = 0
          this.updateTransformer(true)
          return
          }
  
          this.selectedShapeId = id;
        } else {
          this.selectedShapeId = -1;
        }
        this.updateTransformer(false);
      },
      updateTransformer(removeTransformer) {
        // here we need to manually attach or detach Transformer node
        const transformerNode = this.$refs.transformer.getNode();
        const stage = transformerNode.getStage();
        const { selectedShapeId } = this;
  
        const selectedNode = stage.findOne('#' + selectedShapeId);
  
        if(removeTransformer){
          transformerNode.nodes([]);
          return;
        }
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
      
  
  
      async undo (){
          this.deleteFlag = 0
          this.copyFlag = 0
          this.colorFlag = 0
          this.updateTransformer(true)
          this.rectangles= []
          this.circles= []
          this.ellipses= []
          this.triangles= []
          this.pentagons= []
          this.hexagons= []
          this.lines= []
          const responcee = await fetch("http://localhost:8081/paint/undo",{
              method:"POST",
              headers : {
                  'Content-type':'application/json',
              },
              }).then(responce=>{
              return responce.text();
              }).then(data =>{
              this.allshapes = JSON.parse(data);
              }).catch(error => {
              console.error("Error:", error);
              this.isError = true;
          });
          this.allshapes.forEach((arrayItem) => {
              if(arrayItem.name === "square"){
                  this.rectangles.push(arrayItem)
              }   
              else if(arrayItem.name === "rectangle")
                  this.rectangles.push(arrayItem)
              else if(arrayItem.name === "triangle")
                  this.triangles.push(arrayItem)
              else if(arrayItem.name === "elipse")
                  this.ellipses.push(arrayItem)
              else if(arrayItem.name === "line")
                  this.lines.push(arrayItem)
              else if(arrayItem.name === "pentagon")
                  this.pentagons.push(arrayItem)
              else if(arrayItem.name === "hexagon")
                  this.hexagons.push(arrayItem)
              else if(arrayItem.name === "circle")
                  this.circles.push(arrayItem)
          });
      },
      async redo (){
          this.colorFlag = 0
          this.copyFlag = 0
          this.deleteFlag= 0
          this.updateTransformer(true)
          this.rectangles= []
          this.circles= []
          this.ellipses= []
          this.triangles= []
          this.pentagons= []
          this.hexagons= []
          this.lines= []
          const responcee = await fetch("http://localhost:8081/paint/redo",{
              method:"POST",
              headers : {
                  'Content-type':'application/json',
              },
              }).then(responce=>{
              return responce.text();
              }).then(data =>{
              this.allshapes = JSON.parse(data);
              }).catch(error => {
              console.error("Error:", error);
              this.isError = true;
          });
          this.allshapes.forEach((arrayItem) => {
              if(arrayItem.name === "square"){
                  this.rectangles.push(arrayItem)
              }   
              else if(arrayItem.name === "rectangle")
                  this.rectangles.push(arrayItem)
              else if(arrayItem.name === "triangle")
                  this.triangles.push(arrayItem)
              else if(arrayItem.name === "elipse")
                  this.ellipses.push(arrayItem)
              else if(arrayItem.name === "line")
                  this.lines.push(arrayItem)
              else if(arrayItem.name === "pentagon")
                  this.pentagons.push(arrayItem)
              else if(arrayItem.name === "hexagon")
                  this.hexagons.push(arrayItem)
              else if(arrayItem.name === "circle")
                  this.circles.push(arrayItem)
          });
      },
      clear (){
          this.colorFlag = 0
          this.copyFlag = 0
          this.deleteFlag = 0
          this.updateTransformer(true)
          this.allshapes = []
          this.rectangles= []
          this.circles= []
          this.ellipses= []
          this.triangles= []
          this.pentagons= []
          this.hexagons= []
          this.lines= []
          fetch("http://localhost:8081/paint/clearAll",{
              method:"POST",
              headers : {
                  'Content-type':'application/json',
              },
              }).then(responce=>{
              return responce.text();
              }).then(data =>{
              console.log(data);
              }).catch(error => {
              console.error("Error:", error);
              this.isError = true;
          });
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
          this.colorFlag = 0
          this.copyFlag = 0
          this.deleteFlag = 0
          var color = document.getElementById("color").value;
  
          //for adjusting position of shapes
          var item1ForWidth  = document.querySelector('.sidenavshape').offsetWidth
          var item2ForWidth  = parseFloat(window.getComputedStyle(document.getElementById('container')).marginLeft)*2
          var item1ForHeight = document.querySelector('.control-header').offsetHeight
          var item2ForHeight = parseFloat(window.getComputedStyle(document.getElementById('container')).marginTop)
          var offsetHeightCanvas = item1ForHeight + item2ForHeight
          var offsetWidthCanvas  = item1ForWidth + item2ForWidth
  
          switch (this.shapeVariable){
              case 1:
              console.log("hi")
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
                      scaleX: 1,
                      scaleY: 1,
                      id: String(this.id)
                  });
                  this.id = String((Number(this.id)+ 1))
                  console.log(square)
                  console.log(this.rectangles)
                  this.rectangles.push(square)
                  this.allshapes.push(square)
  
                  fetch("http://localhost:8081/paint/create",{
                  method:"POST",
                  headers : {
                      'Content-type':'application/json',
                  },
                  body : JSON.stringify(square)
                  }).then(responce=>{
                  return responce.text();
                  }).then(data =>{
                  console.log(data);
                  }).catch(error => {
                  console.error("Error:", error);
                  this.isError = true;
                  });
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
                  this.id = String((Number(this.id)+ 1))
                  this.rectangles.push(rect)
                  this.allshapes.push(rect)
  
                  fetch("http://localhost:8081/paint/create",{
                  method:"POST",
                  headers : {
                      'Content-type':'application/json',
                  },
                  body : JSON.stringify(rect)
                  }).then(responce=>{
                  return responce.text();
                  }).then(data =>{
                  console.log(data);
                  }).catch(error => {
                  console.error("Error:", error);
                  this.isError = true;
                  });
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
                      name: 'elipse',
                      id: String(this.id)
                  });
                  this.id = String((Number(this.id)+ 1))
                  this.ellipses.push(ellipse)
                  this.allshapes.push(ellipse)
                  fetch("http://localhost:8081/paint/create",{
                  method:"POST",
                  headers : {
                      'Content-type':'application/json',
                  },
                  body : JSON.stringify(ellipse)
                  }).then(responce=>{
                  return responce.text();
                  }).then(data =>{
                  console.log(data);
                  }).catch(error => {
                  console.error("Error:", error);
                  this.isError = true;
                  });
                  
                  break;
              case 4: 
                  var triangle = ({
                      x: event.clientX-offsetWidthCanvas,
                      y: event.clientY-offsetHeightCanvas,
                      sides: 3,
                      radius: 100,
                      fill: color,
                      stroke: 'black',
                      draggable: true,
                      strokeWidth: 3,
                      name: 'triangle',
                      id: String(this.id)
                  });
                  this.id = String((Number(this.id)+ 1))
                  this.triangles.push(triangle)
                  this.allshapes.push(triangle)
  
                  fetch("http://localhost:8081/paint/create",{
                  method:"POST",
                  headers : {
                      'Content-type':'application/json',
                  },
                  body : JSON.stringify(triangle)
                  }).then(responce=>{
                  return responce.text();
                  }).then(data =>{
                  console.log(data);
                  }).catch(error => {
                  console.error("Error:", error);
                  this.isError = true;
                  });
                  break;
              case 5: 
                  var circle = ({
                      x: event.clientX-offsetWidthCanvas,
                      y: event.clientY-offsetHeightCanvas,
                      radius: 60,
                      fill: color,
                      stroke: 'black',
                      draggable: true,
                      strokeWidth: 3,
                      name: 'circle',
                      id: String(this.id)
                  });
                  this.id +=1
                  this.circles.push(circle)
                  this.allshapes.push(circle)
  
                  fetch("http://localhost:8081/paint/create",{
                  method:"POST",
                  headers : {
                      'Content-type':'application/json',
                  },
                  body : JSON.stringify(circle)
                  }).then(responce=>{
                  return responce.text();
                  }).then(data =>{
                  console.log(data);
                  }).catch(error => {
                  console.error("Error:", error);
                  this.isError = true;
                  });
                  break;
              case 6: 
                  var line = ({
                      points: [event.clientX-100-offsetWidthCanvas, event.clientY-100-offsetHeightCanvas,
                              event.clientX+100-offsetWidthCanvas, event.clientY+100-offsetHeightCanvas],
                      stroke: color,
                      strokeWidth: 10,
                      lineCap: 'round',
                      lineJoin: 'round',
                      draggable: true,
                      name: 'line',
                      id: String(this.id)
                  });
                  this.id +=1
                  this.lines.push(line)
                  this.allshapes.push(line)
  
                  fetch("http://localhost:8081/paint/create",{
                  method:"POST",
                  headers : {
                      'Content-type':'application/json',
                  },
                  body : JSON.stringify(line)
                  }).then(responce=>{
                  return responce.text();
                  }).then(data =>{
                  console.log(data);
                  }).catch(error => {
                  console.error("Error:", error);
                  this.isError = true;
                  });
                  break;
              case 7:
                  var pentagon = ({
                      x: event.clientX-offsetWidthCanvas,
                      y: event.clientY-offsetHeightCanvas,
                      sides: 5,
                      radius: 70,
                      fill: color,
                      stroke: 'black',
                      draggable: true,
                      strokeWidth: 3,
                      name: 'pentagon',
                      id: String(this.id)
                  });
                  this.id = String((Number(this.id)+ 1))
                  this.pentagons.push(pentagon)
                  this.allshapes.push(pentagon)
  
                  fetch("http://localhost:8081/paint/create",{
                  method:"POST",
                  headers : {
                      'Content-type':'application/json',
                  },
                  body : JSON.stringify(pentagon)
                  }).then(responce=>{
                  return responce.text();
                  }).then(data =>{
                  console.log(data);
                  }).catch(error => {
                  console.error("Error:", error);
                  this.isError = true;
                  });
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
                      draggable: true,
                      name: 'hexagon',
                      id: String(this.id)
                  });
                  this.id = String((Number(this.id)+ 1))
                  this.hexagons.push(hexagon)
                  this.allshapes.push(hexagon)
      
                  fetch("http://localhost:8081/paint/create",{
                  method:"POST",
                  headers : {
                      'Content-type':'application/json',
                  },
                  body : JSON.stringify(hexagon)
                  }).then(responce=>{
                  return responce.text();
                  }).then(data =>{
                  console.log(data);
                  }).catch(error => {
                  console.error("Error:", error);
                  this.isError = true;
                  });
                  break;
          }
          this.shapeVariable = 0
      },
      async save() {
          this.deleteFlag = 0
          this.copyFlag = 0
          this.colorFlag = 0
          this.updateTransformer(true)
        try {
          const path = this.path + "\\" + this.name + "." + this.format;
          const idCounter = '' + this.id;
          console.log(path);
          const response = await fetch('http://localhost:8081/paint/save', {
            method: 'POST',
            headers: {
               'Content-Type': 'application/x-www-form-urlencoded',
             },
            body: new URLSearchParams({
              path: path,
              idCounter: idCounter,
            }), 
          }).then(responce=>{
                  return responce.text();
                  }).then(data =>{
                  console.log(data);
                  }).catch(error => {
                  console.error("Error:", error)});
          } catch (error) {
              console.error('Error saving data:', error);
          }
      },
  
      async load() {
          this.deleteFlag = 0
          this.copyFlag = 0
          this.colorFlag = 0
          this.updateTransformer(true)
  
        try {
          const path = this.path;
          const response = await fetch('http://localhost:8081/paint/load', {
            method: 'POST',
            headers: {
              'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: new URLSearchParams({
              path: path,
            }),
          });
  
          if (response.ok) {
            const loadedSave = await response.json();
            console.log('Board is Loaded:', loadedSave);
            this.id = loadedSave.idCounter
            this.allshapes = loadedSave.lastUpdate
          } else {
            console.error('Failed to load board');
            return;
          }
        } catch (error) {
          console.error('Error:', error);
        }
          this.rectangles= []
          this.circles= []
          this.ellipses= []
          this.triangles= []
          this.pentagons= []
          this.hexagons= []
          this.lines= []
          
          this.allshapes.forEach((arrayItem) => {
              if(arrayItem.name === "square")
                  this.rectangles.push(arrayItem)
              else if(arrayItem.name === "rectangle")
                  this.rectangles.push(arrayItem)
              else if(arrayItem.name === "triangle")
                  this.triangles.push(arrayItem)
              else if(arrayItem.name === "elipse")
                  this.ellipses.push(arrayItem)
              else if(arrayItem.name === "line")
                  this.lines.push(arrayItem)
              else if(arrayItem.name === "pentagon")
                  this.pentagons.push(arrayItem)
              else if(arrayItem.name === "hexagon")
                  this.hexagons.push(arrayItem)
              else if(arrayItem.name === "circle")
                  this.circles.push(arrayItem)
          });
  
      }
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
      background: linear-gradient(to right,#b2d0e8,rgb(106, 133, 182) );
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
  
  .control-header {
      display: flex;
  }
  
  header{
      width: calc(900px * 0.6);
      height: calc(130px * 0.6);
      align-items: center;
      padding: calc(16px * 0.6);
      color: white;
      border-style:ridge;
      border-radius: calc(50px * 0.6);
      background: linear-gradient(to top,#394b9a 0%,#a7c9e0 );
      margin: auto;
      margin-top: calc(20px * 0.6);
      border-color: black;
  
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
      background: #e9eef0;
      margin-left: calc(3px * 0.6);
      align-content: center;
      align-items: center;
      justify-content: center;  
  }
  
  #load{
      margin-left: calc(390px * 0.6);
  }
  
  #save:hover, #load:hover{
      background-color: rgb(0, 0, 0);
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
      color: #000000;
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
      color:rgb(2, 0, 2);
      font-size: calc(20px * 0.6);
      cursor: pointer;
      background: #e9eef0;
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
      background-color: rgb(0, 0, 0);
      color: rgb(243, 290, 255);
  }
  
  .sidenavoption {
      display: flex;
      flex-direction: column;
      align-items: center;
      width: 10%;
      padding:calc(5px * 0.6) calc(30px * 0.6) calc(20px * 0.6) calc(30px * 0.6);
      height:calc(600px * 0.6);
      margin: calc(130px * 0.6) calc(15px * 0.6) calc(15px * 0.6) calc(15px * 0.6);
      background: linear-gradient(to right,#394b9a 0%,#a7c9e0 );
      padding-top: calc(20px * 0.6);
      border-radius: calc(50px * 0.6);
      border-style:ridge;
      border-color: black;
  }
  
  .sidenavoption button {
      padding: calc(8px * 0.6) calc(25px * 0.6);
      text-align: center;
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
      margin: calc(130px * 0.6) calc(15px * 0.6) calc(15px * 0.6) calc(15px * 0.6);
      background: linear-gradient(to left,#394b9a 0%,#a7c9e0 );
      padding-top: calc(20px * 0.6);
      border-radius: calc(50px * 0.6);
      border-style:ridge;
      border-color: black;
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
      width: 37%;
      
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
      width: 37%;
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
      width: calc(37% * 0.6);
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
      accent-color:  rgb(5, 0, 6);
  }
  
  #extension{
      display: inline-flex;
      margin-left:calc(70px * 0.6);
  }
  
  .imagesmain1{
      float: left;
      width: 200px;
      height: 150px; 
  }
  
  .imagesmain2{
      float: right;
      width: 200px;
      height: 150px; 
  }
  
  #clear{
      width: 72px;
  }
  
  .draw {
      display: flex;
      flex-direction: column;
  }
  </style>