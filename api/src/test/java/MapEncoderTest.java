import me.combimagnetron.sunscreen.ui.graphic.GraphicLike;
import me.combimagnetron.sunscreen.ui.graphic.modifier.GraphicModifier;
import me.combimagnetron.sunscreen.ui.render.engine.encode.MapEncoder;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

@Suite
@SuiteDisplayName("MapEncoder")
@SelectClasses({ GraphicLike.class, MapEncoder.class })
public class MapEncoderTest {

    @Test
    public void mapEncoder() throws IOException {
        BufferedImage image = ImageIO.read(new URL("https://i.imgur.com/4zDrYvx.png"));
        GraphicLike<?> graphicLike = new GraphicLike() {
            @Override
            public @NotNull GraphicLike<?> modifier(@NotNull GraphicModifier modifier) {
                return this;
            }

            @Override
            public @NotNull BufferedImage image() {
                return image;
            }
        };
        ArrayList<Float> times = new ArrayList<>();
        for (int i = 0; i < 100_000; i++) {
            long nanos = System.nanoTime();
            MapEncoder mapEncoder = new MapEncoder(graphicLike);
            times.add(((float) System.nanoTime() - nanos)/1_000_000);
        }
        System.out.println(times.stream().mapToDouble(Float::doubleValue).average());
    }

}
