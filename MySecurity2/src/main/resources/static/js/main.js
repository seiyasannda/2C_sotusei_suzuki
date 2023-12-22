    window.onload = function(){
        Quagga.init({
            inputStream: {
                type: "LiveStream",
                target: document.querySelector('#container')
            },
            constraints: {
                facingMode: "environment",
            },
            decoder: {
                readers: [ "ean_reader" ]
            } 
        }, 
        function(err) {
            if (err) {
                console.log(err);
                return;
            }
            console.log("Initialization finished. Ready to start");
            Quagga.start();
        });

        Quagga.onProcessed(function(result){
            var ctx = Quagga.canvas.ctx.overlay;
            var canvas = Quagga.canvas.dom.overlay;

            ctx.clearRect(0, 0, parseInt(canvas.width), parseInt(canvas.height));

            if (result) {
                if (result.box) {
                    console.log(JSON.stringify(result.box));
                    Quagga.ImageDebug.drawPath(result.box, {x: 0, y: 1}, ctx, {color: 'blue', lineWidth: 2});
                }
            }
        });

        Quagga.onDetected(function(result){
            document.querySelector('#result').textContent = result.codeResult.code;
        });      
    };
