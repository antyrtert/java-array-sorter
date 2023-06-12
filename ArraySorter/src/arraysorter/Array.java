package arraysorter;

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;

/**
 * Array container
 * @author Voronin Daniil
 */
public final class Array {
    private double[] data;
    private byte[] color;
    public Event update = new Event();
    
    public Array(int length) {
        init(length);
    }
    
    private void init(int length) {
        if (data == null || data.length != length) {
            alloc(length);
        }
    }
    
    public void alloc(int length) {
        data = new double[length];
        color = new byte[length];
    }
    
    public void clear() {
        init(0);
    }
    
    public int getLength() {
        return data.length;
    }
    
    public double get(int index) {
        updateColor(index, 0x0e);
        if (Double.isFinite(data[index])) {
            return data[index];
        }
        return Double.NEGATIVE_INFINITY;
    }
    
    public void set(int index, double value) {
        data[index] = value;
        updateColor(index, 0xe0);
    }
    
    public void swap(int src, int dst) {
        var temp = data[src];
        data[src] = data[dst];
        data[dst] = temp;
        updateColor(new int[] { src, dst }, 0xee);
    }
    
    public void shift(int start, int end) {
        shift(start, end, 1);
    }
    
    public void shift(int start, int end, int offset) {
        for (int i = end; i > start; i--){
            double temp = data[i - 1];
            data[i - 1] = data[i];
            data[i] = temp;
        }
        int[] shifted = new int[end - start + 1];
        for (int i = 0; i < shifted.length; i++) {
            shifted[i] = i + start;
        }
        updateColor(shifted, 0xee);
    }
    
    private Color getColor(int index) {
        int c = color[index] & 0xff;
        c |= ((c & 0xf0) | ((c & 0xf0) << 4)) << 16;
        c |= ((c & 0x0f) | ((c & 0x0f) << 4)) << 8;
        c &= 0xffff00;
        c |= 0x181818;
        return new Color(c);
    }
    
    private void updateColor(int index, int color) {
        updateColor(new int[] { index }, color);
    }
    
    private void updateColor(int[] indexes, int color) {
        for (int i = 0; i < this.color.length; i++) {
            int c = this.color[i] & 0xff;
            if ((c & 0xf0) >= 0x20) c -= 0x20; else c &= 0x0f;
            if ((c & 0x0f) >= 0x04) c -= 0x04; else c &= 0xf0;
            this.color[i] = (byte)c;
        }
        for (int index : indexes) {
            this.color[index] |= color;    
        }
        update.invoke();
    }
    
    public void tryParseArray(String text) {
        String[] data = text.split("\\s*[;, \n]+\\s*");
        init(data.length);
        
        for (int i = 0; i < data.length; i++) {
            this.data[i] = tryParseDouble(data[i]);
        }
    }
    
    public static double tryParseDouble(String text) {
        try {
            return Double.parseDouble(text);
        } catch (NumberFormatException e) {
            return Double.NaN;
        }
    }
    
    public static float tryParseFloat(String text) {
        try {
            return Float.parseFloat(text);
        } catch (NumberFormatException e) {
            return Float.NaN;
        }
    }
    
    public static int tryParseInt(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    
    public void render(BufferedImage img) {
        if (data.length == 0) return;

        // get graphics and font
        Graphics2D g = (Graphics2D)img.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
        g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        FontMetrics f = g.getFontMetrics();
        
        // find maximum and minimum values
        double min = Double.POSITIVE_INFINITY, max = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < data.length; i++) {
            if (data[i] == Double.NaN) continue;
            if (data[i] < min) min = data[i];
            if (data[i] > max) max = data[i];
        }

        // width - pixels per 1 unit horizontally, 
        // height - pixels per 1 unit vertically,
        // offset - 1px gap between bars
        double width  = (double)img.getWidth() / data.length,
               height = (double)img.getHeight() / (++max - --min),
               offset = 2 * Math.min(Math.max(width * 0.5f - 1, 0), 0.5f);
        
        // draw histogram bars
        for (int i = 0; i < data.length; i++) {
            double x = width * i + offset, y = (max - data[i]) * height,
                   w = width     - offset, h = (data[i] - min) * height,
                   delta = (i > 0 ? data[i] - data[i - 1] : h) * height;
            
            var c = getColor(i);

            // draw bar
            g.setColor(c);
            g.fill(new Rectangle2D.Double(x, y + w + offset, w, h));
            
            // draw peek
            g.setColor(c.brighter().brighter().brighter());
            g.fill(new Rectangle2D.Double(x, y, w, w));
        }
        
        // draw zero-line
        if (max >= 0 && 0 >= min) {
            g.setColor(Color.gray);
            
            double x1 = 0,              y1 = max * height,
                   x2 = img.getWidth(), y2 = y1;
            
            float o = f.getHeight() - 4,
                  x = (float)x1 + o, y = (float)y1 + o;
            
            g.draw(new Line2D.Double(x1, y1, x2, y2));
            g.drawString("0", x, y);
        }
        
        // calculate font size to fit
        float fontSize = (float) height;
        for (String str : toString().split(", ")) {
            // approximate size to fit horizontally
            float size = f.getHeight();
            for (int k = 0; k < 5; k++) {
                Font fnt = f.getFont().deriveFont(size);
                FontMetrics m = g.getFontMetrics(fnt);
                size *= width / m.stringWidth(str);
            }

            fontSize = Math.min(fontSize, size);
        }

        // draw numbers on bars
        if (fontSize >= 3) {
            // scale current font
            Font fnt = f.getFont().deriveFont(fontSize);
            FontMetrics m = g.getFontMetrics(fnt);
            
            // set target font
            g.setFont(fnt);
            g.setColor(Color.GRAY);

            for (int i = 0; i < data.length; i++) {
                String str = doubleToString(data[i]);

                float o = (float)(width - m.stringWidth(str)) / 2,
                      x = (float)(width * i + o),
                      y = (float)((max - data[i]) * height) - 2;
                
                g.drawString(str, x, y);
            }

            // restore font
            g.setFont(f.getFont());
        }
        
        g.dispose();
    }
    
    private String doubleToString(double value) {
        return String.format("%.13g", value).replaceFirst("[,.]?0+(?=$|e)", "").replace(",", ".");
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder().append(doubleToString(data[0]));
        for (int i = 1; i < data.length; i++) {
            builder.append(", ").append(doubleToString(data[i]));
        }
        return builder.toString();
    }
}
