function AugmentScroll(type /* slowest|slower|slow|fast|faster|fastest */) {
    this.oldX = window.pageXOffset || document.body.scrollLeft || document.documentElement.scrollLeft || 0;
    this.oldY = window.pageYOffset || document.body.scrollTop || document.documentElement.scrollTop || 0;
    this.deltaX = 0;
    this.deltaY = 0;
    this.busy = false;
    this.multiplier = 0;
    switch(type) {
        case 'slowest': this.multiplier = 1.0; break;
        case 'slower': this.multiplier = 4; break;
        case 'slow': this.multiplier = 2; break;
        case 'fast': this.multiplier = 1; break;
        case 'faster': this.multiplier = 2; break;
        case 'fastest': this.multiplier = 4; break;
        default: break;
    }
    window.addEventListener("touchstart", this.OnScroll.bind(this), false);
    window.addEventListener("touchmove", function(){
        this.busy = true
    }, false);
    window.addEventListener("touchend", function(){
        this.busy = true
    }, false);
    window.addEventListener("touchcancel", function(){
        this.busy = true
    }, false);
    window.setInterval(this.OnUpdate.bind(this), 500);
};
AugmentScroll.prototype.OnScroll = function() {
  console.log("On Scroll")

    if(this.busy) {
        this.busy = false;
    } else {
        var x = window.pageXOffset || document.body.scrollLeft || document.documentElement.scrollLeft || 0;
        var y = window.pageYOffset || document.body.scrollTop || document.documentElement.scrollTop || 0;
        this.deltaX = x - this.oldX;
        this.deltaY = y - this.oldY;
    }
    return true;
};
AugmentScroll.prototype.OnUpdate = function() {
  console.log("On Updated")

    var dx = this.deltaX / this.multiplier;
    var dy = this.deltaY / this.multiplier;
    if(dx != 0 || dy != 0) {
        this.busy = true;
        window.scrollBy(dx, dy);
        this.oldX = window.pageXOffset || document.body.scrollLeft || document.documentElement.scrollLeft || 0;
        this.oldY = window.pageYOffset || document.body.scrollTop || document.documentElement.scrollTop || 0;
        this.busy = false;
    }
};
new AugmentScroll('slowest');

