package org.cl;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.*;

public class Main {
    static Image imageOn = Toolkit.getDefaultToolkit().getImage("on.png");
    static Image imageOff = Toolkit.getDefaultToolkit().getImage("off.png");
    static int INTERVAL_SECONDS = 60;
    static TrayIcon trayIcon = new TrayIcon(imageOn, "Methamphetamine");

    static boolean on = true;

    public static void main(String[] args) throws InterruptedException, AWTException {

        if (SystemTray.isSupported()) {
            SystemTray tray = SystemTray.getSystemTray();

            trayIcon.setImageAutoSize(true);

            final Frame frame = new Frame("");
            frame.setUndecorated(true);
            frame.setResizable(false);
            frame.setVisible(true);

            final PopupMenu popup = createPopupMenu();

            trayIcon.setPopupMenu(popup);
            trayIcon.addMouseListener(new MouseInputListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (SwingUtilities.isRightMouseButton(e)){
                        frame.add(popup);
                    }else if (SwingUtilities.isLeftMouseButton(e)){
                        on = !on;
                        if (on){
                            trayIcon.setImage(imageOn);
                        }else{
                            trayIcon.setImage(imageOff);
                        }
                        trayIcon.displayMessage("Methamphetamine", "Switched: " + (on ? "ON" : "OFF"), TrayIcon.MessageType.INFO);
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {}
                @Override
                public void mouseReleased(MouseEvent e) {}
                @Override
                public void mouseEntered(MouseEvent e) {}
                @Override
                public void mouseExited(MouseEvent e) {}
                @Override
                public void mouseDragged(MouseEvent e) {}
                @Override
                public void mouseMoved(MouseEvent e) {}
            });

            tray.add(trayIcon);
        }

        Robot robot = new Robot();
        while (true) {
            if (on) {
                robot.keyPress(KeyEvent.VK_F24);
                robot.keyRelease(KeyEvent.VK_F24);
                System.out.println("Pressed F24");
            }
            Thread.sleep(INTERVAL_SECONDS * 1000);
        }
    }

    protected static PopupMenu createPopupMenu() {
        final PopupMenu popup = new PopupMenu();
        MenuItem exitItem = new MenuItem("Quit");
        exitItem.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        popup.add(exitItem);
        return popup;
    }

}
