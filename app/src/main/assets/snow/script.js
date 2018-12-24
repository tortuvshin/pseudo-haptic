
scrollingElement = (document.scrollingElement || document.body);
scrollingElement.scrollTop = scrollingElement.scrollHeight/2;
scrollingElement.scrollLeft = scrollingElement.scrollWidth/2;

document.body.addEventListener("touchstart", createFootPrint);

// Touch хийсэн газар хөлийн мөр харуулах
function createFootPrint() {

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
}

var lastScrollTop = scrollingElement.scrollHeight/2;
// element should be replaced with the actual target element on which you have applied scroll, use window in case of no target element.
element.addEventListener("scroll", function(){ // or window.addEventListener("scroll"....
   var st = window.pageYOffset || document.documentElement.scrollTop; // Credits: "https://github.com/qeremy/so/blob/master/so.dom.js#L426"
   if (st > lastScrollTop){
      // downscroll code
      Android.logger("Down scrolled+ "+lastScrollTop - st);
   } else {
      // upscroll code

      Android.logger("Un scrolled+ "+lastScrollTop - st);
   }
   lastScrollTop = st <= 0 ? 0 : st; // For Mobile or negative scrolling
}, false);

