// Рисовалка

package paint.runner;

import paint.view.PainterSingleton;
import static javax.swing.SwingUtilities.invokeLater;

public class Runner {
    public static void main(String[] args) {

        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        invokeLater(new Runnable() {
            public void run() {
                PainterSingleton.getInstance();
            }
        });

    }

}
