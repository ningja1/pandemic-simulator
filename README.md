# Moving Circles Demo (Java Swing)

A tiny demo Java GUI application that renders 10 colorful circles moving randomly and bouncing off the window edges.

Prerequisites
- Java 11+
- Maven

Build and run
1. Compile and run directly with Maven:
   mvn compile exec:java -Dexec.mainClass="com.example.demogui.DemoGuiApp"

   - If you are running on a headless server (no X11 DISPLAY), use Xvfb:
     xvfb-run -s "-screen 0 1024x768x24" mvn compile exec:java -Dexec.mainClass="com.example.demogui.DemoGuiApp"

   - Or use SSH X11 forwarding from a machine with a display:
     ssh -X user@host
     mvn compile exec:java -Dexec.mainClass="com.example.demogui.DemoGuiApp"

2. Or build a jar and run:
   mvn package
   java -cp target/demo-gui-1.0-SNAPSHOT.jar com.example.demogui.DemoGuiApp

What it does
- Small window with a text field, "Greet" button, and a message label.
- Menu: File → Exit, Help → About.

Feel free to extend it with more components, layouts, or migrate to JavaFX.