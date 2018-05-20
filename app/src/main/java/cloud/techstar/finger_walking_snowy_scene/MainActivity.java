package cloud.techstar.finger_walking_snowy_scene;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

    private ImageView snowBackground; // Цасны гадаргуу харуулж байгаа ImageView

    /**
     * Цасан дээр алхаж байгаа мэт мэдрэмжийг төрүүлэхийн тулд хэрэглэгч дэлгэц дээр
     * ямар хэсэгт хүрсэн /X, Y координатуудын хувьд/, тэнхлэгийн утга нь байрлал болон бусад
     * хөдөлгөөний шинж чанарын хувьд хэрхэн өөрчлөгдсөн бэ гэдгийг мэдэх хэрэгтэй болно.
     * Ингэхийн тулд дараах X, Y тэнхлэгийн утга эхний удаа дэлгэцэнд хүрхэд ямар байсан
     * дараа нь хэрхэн өөрчлөгдсөн бэ гэдгийг мэдэх хэрэгтэй болно
     * */
    private float y; // Y тэнхлэгт эхлээд хаана хүрсэн
    private float dy; // Y тэнхлэгт дараа нь хаана хүрч утга нь өөрчлөгдсөн
    private float x; // X тэнхлэгт эхлээд хаана хүрсэн
    private float dx; // X тэнхлэгт дараа нь хаана хүрч утга нь өөрчлөгдсөн

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Background background = new Background(this);
        setContentView(background);

//        snowBackground = (ImageView) findViewById(R.id.snow_background);
//        final TextView valueText = (TextView) findViewById(R.id.y_value);
//        final MediaPlayer mp = MediaPlayer.create(this, R.raw.snow);
//
//        // Background дээр хүрэх event ийг барих
//        snowBackground.setOnTouchListener(new View.OnTouchListener() {
//            @SuppressLint("SetTextI18n")
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
////                mp.start();
//
//                /** Хэрэглэгч дэлгэцэн дээр хүрэх үед систем холбогдох эвэнтүүдийг шалгах
//                 * Доошоо, дээшээ, баруун, зүүн гэх мэт олон төрлийн хөдөлгөөнүүдийг MotionEvent-ийн
//                 * тусламжтайгаар барьж авна
//                 * {@link https://developer.android.com/reference/android/view/MotionEvent}.
//                 */
//                switch (event.getAction()) {
//
//                    /**
//                     * y, dy, x, dy утгууд ямар учиртай вэ гэвэл
//                     * Алхах хөдөлгөөн хийхийн тулд эхний хөл гишгээд дараагийн хөл урд гишгэдэг
//                     * y,x утга дээр эхлээд хаана гишгэсэн
//                     * dy, dx утга дээр дараа нь хаана гишгэсэн гэдгийг авч байна гэж ойлгох хэрэгтэй
//                     * Арын цасыг хэрхэн хөдөлгөх вэ гэвэл эхний гишгэсэн утгаас дараагийн утгыг хасаад ялгаварын хэмжээгээр
//                     * арын зургаа Y тэнхлэгийн дагуу хөдөлгөнө гэсэн үг
//                     * */
//                    case MotionEvent.ACTION_DOWN: {
//                        y = event.getY(); // А
//                        dy = y - snowBackground.getY(); //
//                        x = event.getX();
//                        dx = x - snowBackground.getX();
//
//                    }
//                    break;
//                    case MotionEvent.ACTION_MOVE: {
//                        snowBackground.setY(event.getY() - dy);
//
////                        snowBackground.setX(event.getX() - dx);
//
//                        valueText.setText("BEFORE VALUE:  Y "+y +" X "+x +
//                                "\nCURRENT VALUE: Y "+ event.getY()+ " X "+event.getX()+
//                                "\nCHANGED VALUE: Y "+ (event.getY()-dy) + " X "+(event.getX() - dx));
//                    }
//                    break;
//                    case MotionEvent.ACTION_UP: {
//                        //your stuff
//                    }
//                    return true;
//                }
//                return true;
//            }
//
//        });
    }
}

