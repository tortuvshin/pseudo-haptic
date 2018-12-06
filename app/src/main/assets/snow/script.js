    scrollingElement = (document.scrollingElement || document.body);
    scrollingElement.scrollTop = scrollingElement.scrollHeight/2;
    scrollingElement.scrollLeft = scrollingElement.scrollWidth/2;

    document.body.addEventListener("touchstart", touch);

    function touch() {

        var rect = document.body.getBoundingClientRect();
        x = event.touches[0].clientX - rect.left;
        y = event.touches[0].clientY - rect.top;

        var div = document.createElement('div');
        div.style.backgroundImage = "url('footprint.png')";
        div.style.position = "absolute";
        div.style.width = '52px';
        div.style.height = '121px';
        div.style.left = x+'px';
        div.style.top = y+'px';
        document.body.appendChild(div);

        Android.soundStart();
        Android.vibrationStart();

        window.onscroll = function(){
          checkScrollSpeed();
        };
    }

    var checkScrollSpeed = (function(settings){
        settings = settings || {};

        var lastPos, newPos, timer, delta,
            delay = settings.delay || 50; // in "ms" (higher means lower fidelity )

        function clear() {
          lastPos = null;
          delta = 0;
        }

        clear();

        return function(){
          newPos = window.scrollY;
          if ( lastPos != null ){ // && newPos < maxScroll
            delta = newPos -  lastPos;
          }
          lastPos = newPos;
          clearTimeout(timer);
          timer = setTimeout(clear, delay);
          return delta;
        };
    })();
