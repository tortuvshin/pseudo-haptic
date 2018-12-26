
scrollingElement = (document.scrollingElement || document.body);
scrollingElement.scrollTop = scrollingElement.scrollHeight/2;
scrollingElement.scrollLeft = scrollingElement.scrollWidth/2;

//var startPageX;
//var startPageY;
//var startScreenX;
//var startScreenY;
//var movedDistance;

document.body.addEventListener('touchstart', function(e){
//    var touchObj = e.changedTouches[0] // reference first touch point (ie: first finger)
//    startScreenX = touchObj.clientX
//    startScreenY = touchObj.clientY // get position of touch point relative to left edge of browser
      createFootPrint()
//    e.preventDefault()
//
//    startPageX = touchObj.pageX;
//    startPageY = touchObj.pageY;
//
//    console.log("clientX coords: X: " + startScreenX + ", Y: " + startScreenX + " pageX coords: X: " + startPageX + ", Y: " + startPageY);

}, false)

//
//document.body.addEventListener('touchmove', function(e){
//    var touchObj = e.changedTouches[0] // reference first touch point for this event
//    movedDistance = touchObj.clientY - startScreenY
//    e.preventDefault()
//}, false)
//
//document.body.addEventListener('touchend', function(e){
//    var touchObj = e.changedTouches[0] // reference first touch point for this eventx
//
//    console.log("POSITIONS: "+ "X : "+startScreenX+ ", Y: "+startScreenY+ ", Moved: "+movedDistance);
//
//    window.scrollTo(0, touchObj.pageY);
//    console.log("Changed positions: X: " + startPageY + ", Now X: " + touchObj.pageY + ", Moved X: " + (touchObj.pageY-startPageY));
//
//    e.preventDefault()
//}, false)

// Touch хийсэн газар хөлийн мөр харуулах
function createFootPrint() {

    var rect = document.body.getBoundingClientRect();
    x = event.touches[0].pageX;
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

// listen to "scroll" event
window.onscroll = function(){
    checkScrollSpeed()
    console.log( checkScrollSpeed() );
};

jQuery.scrollSpeed = function(step, speed, easing) {

    var $document = $(document),
        $window = $(window),
        $body = $('html, body'),
        option = easing || 'default',
        root = 0,
        scroll = false,
        scrollY,
        scrollX,
        view;

    if (window.navigator.msPointerEnabled)

        return false;

    $window.on('mousewheel DOMMouseScroll', function(e) {

        var deltaY = e.originalEvent.wheelDeltaY,
            detail = e.originalEvent.detail;
            scrollY = $document.height() > $window.height();
            scrollX = $document.width() > $window.width();
            scroll = true;

        if (scrollY) {

            view = $window.height();

            if (deltaY < 0 || detail > 0)

                root = (root + view) >= $document.height() ? root : root += step;

            if (deltaY > 0 || detail < 0)

                root = root <= 0 ? 0 : root -= step;

            $body.stop().animate({

                scrollTop: root

            }, speed, option, function() {

                scroll = false;

            });
        }

        if (scrollX) {

            view = $window.width();

            if (deltaY < 0 || detail > 0)

                root = (root + view) >= $document.width() ? root : root += step;

            if (deltaY > 0 || detail < 0)

                root = root <= 0 ? 0 : root -= step;

            $body.stop().animate({

                scrollLeft: root

            }, speed, option, function() {

                scroll = false;

            });
        }

        return false;

    }).on('scroll', function() {

        if (scrollY && !scroll) root = $window.scrollTop();
        if (scrollX && !scroll) root = $window.scrollLeft();

    }).on('resize', function() {

        if (scrollY && !scroll) view = $window.height();
        if (scrollX && !scroll) view = $window.width();

    });
};

jQuery.easing.default = function (x,t,b,c,d) {

    return -c * ((t=t/d-1)*t*t*t - 1) + b;
};

jQuery.scrollSpeed(10, 10);