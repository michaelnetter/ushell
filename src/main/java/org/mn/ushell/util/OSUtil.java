package org.mn.ushell.util;

/**
 * Implements some OS specific functions
 */
public class OSUtil {

    private static final String WINDOWS_SHELL = "cmd";
    private static final String UNIX_SHELL = "sh";
    private static final String VERSION_AUTHOR = "v0.1 (c) 2008 mn";
    private static final String FALLBACK_BANNER = "ushell";
    private static final String DEFAULT_BANNER = "\u03BC" + "shell";

    /**
     * Get the platform specific shell
     *
     * @return String
     */
    public static String getOSShellCommand() {
        String osName = System.getProperty("os.name").toUpperCase();

        if (osName.contains("WIN")) {
            return WINDOWS_SHELL;
        } else {
            return UNIX_SHELL;
        }
    }

    public static void printBanner() {
        String osName = System.getProperty("os.name").toUpperCase();
        if (osName.contains("WIN") || osName.contains("LINUX") || osName.contains("MAC")) {
            System.out.println(DEFAULT_BANNER + " " + VERSION_AUTHOR);
        } else {
            System.out.println(FALLBACK_BANNER + " " + VERSION_AUTHOR);
        }
    }

}
