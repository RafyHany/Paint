let history = [
    {
      x: 20,
      y: 20,
    }
  ];
  let historyStep = 0;

  const stage = new Konva.Stage({
    container: 'container',
    width: window.innerWidth,
    height: window.innerHeight,
  });

  const layer = new Konva.Layer();
  stage.add(layer);

  const position = history[0];



  const rect = new Konva.Rect({
    x: position.x,
    y: position.y,
    width: 50,
    height: 50,
    fill: 'black',
    draggable: true
  });

  layer.add(rect);

  function handleUndo() {
    if (historyStep === 0) {
      return;
    }
    historyStep -= 1;
    const previous = history[historyStep];
    rect.position(previous);
    layer.batchDraw();
  }

  function handleRedo() {
    if (historyStep === history.length - 1) {
      return;
    }
    historyStep += 1;
    const next = history[historyStep];
    rect.position(next);
    layer.batchDraw();
  }

  function handleDragEnd(e) {
    history = history.slice(0, historyStep + 1);
    const pos = {
      x: e.target.x(),
      y: e.target.y(),
    };
    history = history.concat([pos]);
    historyStep += 1;
    layer.batchDraw();
  }


  document.querySelector('#undo').addEventListener('click', handleUndo)
  document.querySelector('#redo').addEventListener('click', handleRedo);
  rect.on('dragend', handleDragEnd)