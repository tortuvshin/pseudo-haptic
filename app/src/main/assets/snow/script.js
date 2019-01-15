var view = document.getElementById("snow-view");
// Touch хийсэн газар хөлийн мөр харуулах
function createFootPrint() {

    var rect = view.getBoundingClientRect();
    x = event.touches[0].pageX;
    y = event.touches[0].clientY - rect.top;

    var div = document.createElement('div');
    div.style.backgroundImage = "url('footprint.png')";
    div.style.position = "absolute";
    div.style.width = '52px';
    div.style.height = '121px';
    div.style.left = x+'px';
    div.style.top = y+'px';
    view.appendChild(div);

    Android.soundStart();
    Android.vibrationStart();
}

var view, relative,
    cdRatio,
    min, max, offset, reference, pressed, xform;

    view.style[xform] = 'translateY(30000px)';

function setCdRatio(value){
    cdRatio = value;
}

function ypos(e) {
    // touch event
    if (e.targetTouches && (e.targetTouches.length >= 1)) {
        return e.targetTouches[0].clientY;
    }

    // mouse event
    return e.clientY;
}

function scroll(y) {
    offset = (y > max) ? max : (y < min) ? min : y;
    view.style[xform] = 'translateY(' + (-offset) + 'px)';
}

function tap(e) {
    createFootPrint()
    pressed = true;
    reference = ypos(e);
    e.preventDefault();
    e.stopPropagation();
    return false;
}

function drag(e) {
    var y, delta;
    if (pressed) {
        y = ypos(e);
        delta = (reference - y) / cdRatio ;
        if (delta > 2 || delta < -2) {
            reference = y;
            scroll(offset + delta);
        }
    }
    e.preventDefault();
    e.stopPropagation();
    return false;
}

function release(e) {
    pressed = false;
    e.preventDefault();
    e.stopPropagation();
    return false;
}

if (typeof window.ontouchstart !== 'undefined') {
    view.addEventListener('touchstart', tap, {passive: false} );
    view.addEventListener('touchmove', drag, {passive: false} );
    view.addEventListener('touchend', release, {passive: false} );
}
view.addEventListener('mousedown', tap, {passive: false} );
view.addEventListener('mousemove', drag, {passive: false} );
view.addEventListener('mouseup', release, {passive: false} );

max = parseInt(getComputedStyle(view).height, 10) - innerHeight;
min = 0;
offset = 20000;
cdRatio = 2
console.log(max);
pressed = false;

relative = (innerHeight - 30) / max;

xform = 'transform';
['webkit', 'Moz', 'O', 'ms'].every(function (prefix) {
    var e = prefix + 'Transform';
    if (typeof view.style[e] !== 'undefined') {
        xform = e;
        return false;
    }
    return true;
});

view.style[xform] = 'translateY(' + (-offset) + 'px)';