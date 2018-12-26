
scrollingElement = (document.scrollingElement || document.body);
scrollingElement.scrollTop = scrollingElement.scrollHeight/2;
scrollingElement.scrollLeft = scrollingElement.scrollWidth/2;

var startPageX;
var startPageY;
var startScreenX;
var startScreenY;
var movedDistance;

document.body.addEventListener('touchstart', function(e){
//    var touchObj = e.changedTouches[0] // reference first touch point (ie: first finger)
//    startScreenX = touchObj.clientX
//    startScreenY = touchObj.clientY // get position of touch point relative to left edge of browser
      createFootPrint()
//    e.preventDefault()
////
////    startPageX = touchObj.pageX;
////    startPageY = touchObj.pageY;
////
////    console.log("clientX coords: X: " + startScreenX + ", Y: " + startScreenX + " pageX coords: X: " + startPageX + ", Y: " + startPageY);
//    forEachChangedFinger(e, function(x,y, id) {
//        createDiv(finger, id);
//        moveBox(id, e2);
//    });
})


document.body.addEventListener('touchmove', function(e){
//    var touchObj = e.changedTouches[0] // reference first touch point for this event
//    movedDistance = touchObj.clientY - startScreenY
//    e.preventDefault(); // prevent page scroll
//
//    forEachChangedFinger(e, function(x,y, id) {
//        moveBox(id, x,y);
//    });
})

document.body.addEventListener('touchend', function(e){
//    var touchObj = e.changedTouches[0] // reference first touch point for this eventx
//
//    console.log("POSITIONS: "+ "X : "+startScreenX+ ", Y: "+startScreenY+ ", Moved: "+movedDistance);
//
//    window.scrollTo(0, touchObj.pageY);
//    console.log("Changed positions: X: " + startPageY + ", Now X: " + touchObj.pageY + ", Moved X: " + (touchObj.pageY-startPageY));
//
//    e.preventDefault()

})

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

//function forEachChangedFinger(e, cb) {
//
//    for (var i = 0; i < e.changedTouches.length; i++) {
//        var finger = e.changedTouches[i];
//        var x = event.touches[i].pageX;
//        var y = event.touches[i].pageY;
//        var id = finger.identifier;
//        cb(finger, id);
//    }
//}

//function createDiv(finger,id) {
//    var div = document.createElement('div');
//    div.style.backgroundImage = "url('footprint.png')";
//    div.style.position = "absolute";
//    div.style.width = '52px';
//    div.style.height = '121px';
//    div.style.left = finger.pageX+'px';
//    div.style.top = finger.pageY+'px';
//    div.id(id);
//    document.body.appendChild(div);
//}
//function moveBox(id, x,y) {
//    var div = $("#"+id);
//    var off = $("body").offset();
//    // offset box a little so it can be seen under a finger!
//    var a = x - off.left - 35;
//    var b = y - off.top - 35;
//    div.css({"left":a, "top":b});
//}
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
//
//var lastScrollPosition = document.body.scrollTop,
//    sectionNo = 0;
//
//function doScroll(scrollPosition, step, first) {
//    var height = window.innerHeight,
//        min = height * sectionNo,
//        max = height * (sectionNo + 1);
//
//    scrollPosition += step;
//
//    if (min < scrollPosition && scrollPosition < max) {
//        // here should be some animation control
//        document.body.scrollTop = scrollPosition;
//        // Call next animation frame
//        window.requestAnimationFrame(doScroll.bind(null, scrollPosition, step));
//    } else {
//        // It fires, when scroll is done
//        lastScrollPosition = scrollPosition;
//        document.addEventListener("scroll", scrollListener);
//    }
//}
//function scrollListener(e) {
//    var scrollPosition = document.body.scrollTop,
//        step;
//
//    // Stop scroll listening
//    document.removeEventListener("scroll", scrollListener);
//
//    // Get direction
//    step = scrollPosition >= lastScrollPosition ? 1 : -1;
//    step = step * 2;
//    // Go back to initial position
//    document.body.scrollTop = lastScrollPosition;
//
//    // Animate
//    window.requestAnimationFrame(doScroll.bind(null, lastScrollPosition, step, true));
//}
//
//document.addEventListener("scroll", scrollListener);
//
//
