package com.ps.ui;

public class UIEffects {
    public static boolean animationsEnabled = true;

    /* User interface - Touch Up Methods*/

    //print text in typewriter style
    static void printTypeWriter(String text, int delay) {
        if(!animationsEnabled) {
            System.out.print(text);
            return;
        }

        for(char c: text.toCharArray()) { //convert string into char array
            System.out.print(c); //print each character
            try {
                Thread.sleep(delay); //delay between characters
            } catch (InterruptedException e) {
                UserInterface.writeErrorsToLogsFile(e);
            }

        }
    }

    //print text then later delete/overwrite
    static void eraseTypeWriter(String text, int delay) {
        if(!animationsEnabled) {
            System.out.print(text);
            return;
        }

        for (int i = text.length() - 1; i >= 0; i--) {
            System.out.print("\b \b"); // backspace, space, backspace (to fully erase the char)
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    //loading animation
    static void spinner(int duration) {
        if(!animationsEnabled) {
            return;
        }

        String[] spinnerChars = {"⠋", "⠙", "⠹", "⠸", "⠼", "⠴", "⠦", "⠧", "⠇", "⠏"};

        long end = System.currentTimeMillis() + duration;
        int i = 0;

        while (System.currentTimeMillis() < end) {
            System.out.print("\r" + spinnerChars[i++ % spinnerChars.length]);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.print("\r ");  // print a space to overwrite spinner char
        System.out.print("\r"); // Clear spinner
    }

    //loading animation w/in lines w/ characters
    static void spinnerInLine(int duration) {
        if(!animationsEnabled) {
            return;
        }

        String[] spinnerChars = {"⠋", "⠙", "⠹", "⠸", "⠼", "⠴", "⠦", "⠧", "⠇", "⠏"};
        long end = System.currentTimeMillis() + duration;
        int i = 0;

        while (System.currentTimeMillis() < end) {
            System.out.print(spinnerChars[i++ % spinnerChars.length]);
            System.out.flush();
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                UserInterface.writeErrorsToLogsFile(e);
            }
            System.out.print("\b"); // backspace to overwrite just the spinner character
        }
    }

    // Dot loader that erases the dots after each cycle
    static void dotTypewriterInLine(String message, int repeatCount) {
        if(!animationsEnabled) {
            return;
        }

        printTypeWriter(message, 50); // Type out the message once

        for (int i = 0; i < repeatCount; i++) {
            for (int dots = 1; dots <= 3; dots++) {
                System.out.print("\r" + message + ".".repeat(dots));
                System.out.flush();

                try {
                    Thread.sleep(300); // Speed between dot steps
                } catch (InterruptedException e) {
                    UserInterface.writeErrorsToLogsFile(e);
                }
            }

            // After 3 dots, erase the dots only (keep message)
            System.out.print("\r" + message + "   \r" + message);
            System.out.flush();
        }
    }

    // Dot loader that keeps the 3 dots at the end
    static void dotTypewriterInLineKeepDots(String message, int repeatCount) {
        if(!animationsEnabled) {
            System.out.print("...");
            return;
        }

        printTypeWriter(message, 50); // Type out the message once

        for (int i = 0; i < repeatCount; i++) {
            for (int dots = 1; dots <= 3; dots++) {
                System.out.print("\r" + message + ".".repeat(dots));
                System.out.flush();

                try {
                    Thread.sleep(300); // Speed between dot steps
                } catch (InterruptedException e) {
                    UserInterface.writeErrorsToLogsFile(e);
                }
            }
        }

        // After all repeats, keep the message with 3 dots permanently
        System.out.print("\r" + message + "...");
        System.out.flush();
    }

    //wait for user to finish reading
    static void pause() {
        if(!animationsEnabled) {
            return;
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            UserInterface.writeErrorsToLogsFile(e);
        }
    }

}
