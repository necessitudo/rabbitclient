import org.tanukisoftware.wrapper.WrapperListener
import org.tanukisoftware.wrapper.WrapperManager

class Main/*---------------------------------------------------------------
     * Constructors
     *-------------------------------------------------------------*/
private constructor() : WrapperListener {
    private var m_app: Application? = null

    /*---------------------------------------------------------------
     * WrapperListener Methods
     *-------------------------------------------------------------*/
    /**
     * The start method is called when the WrapperManager is signaled by the
     * native Wrapper code that it can start its application.  This
     * method call is expected to return, so a new thread should be launched
     * if necessary.
     *
     * @param args List of arguments used to initialize the application.
     *
     * @return Any error code if the application should exit on completion
     * of the start method.  If there were no problems then this
     * method should return null.
     */
    override fun start(args: Array<String>): Int? {
        m_app = Application(args)
        m_app!!.start()

        return null
    }

    /**
     * Called when the application is shutting down.  The Wrapper assumes that
     * this method will return fairly quickly.  If the shutdown code code
     * could potentially take a long time, then WrapperManager.signalStopping()
     * should be called to extend the timeout period.  If for some reason,
     * the stop method can not return, then it must call
     * WrapperManager.stopped() to avoid warning messages from the Wrapper.
     *
     * @param exitCode The suggested exit code that will be returned to the OS
     * when the JVM exits.
     *
     * @return The exit code to actually return to the OS.  In most cases, this
     * should just be the value of exitCode, however the user code has
     * the option of changing the exit code if there are any problems
     * during shutdown.
     */
    override fun stop(exitCode: Int): Int {
        m_app!!.stop()

        return exitCode
    }

    /**
     * Called whenever the native Wrapper code traps a system control signal
     * against the Java process.  It is up to the callback to take any actions
     * necessary.  Possible values are: WrapperManager.WRAPPER_CTRL_C_EVENT,
     * WRAPPER_CTRL_CLOSE_EVENT, WRAPPER_CTRL_LOGOFF_EVENT, or
     * WRAPPER_CTRL_SHUTDOWN_EVENT
     *
     * @param event The system control signal.
     */
    override fun controlEvent(event: Int) {
        if (event == WrapperManager.WRAPPER_CTRL_LOGOFF_EVENT && (WrapperManager.isLaunchedAsService() || WrapperManager.isIgnoreUserLogoffs())) {
            // Ignore
        } else {
            WrapperManager.stop(0)
            // Will not get here.
        }
    }

    companion object {

        /*---------------------------------------------------------------
     * Main Method
     *-------------------------------------------------------------*/
        @JvmStatic
        fun main(args: Array<String>) {
            // Start the application.  If the JVM was launched from the native
            //  Wrapper then the application will wait for the native Wrapper to
            //  call the application's start method.  Otherwise the start method
            //  will be called immediately.
            WrapperManager.start(Main(), args)
        }
    }
}
