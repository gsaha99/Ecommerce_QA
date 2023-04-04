package utilities;

public enum StatusDetails {

	FAIL,

	 /**

	 * Indicates a warning message

	 */

	 WARNING,

	 /**

	 * Indicates that the outcome of a verification was successful

	 */

	 PASS,

	 /**

	 * Indicates a step that is logged into the results for informational

	 * purposes, along with an attached screen shot for reference

	 */

	 ERROR,

	 /**

	 * Indicates a message that is logged into the results for informational

	 * purposes

	 */

	 INFO,

	 /**

	 * Indicates a debug-level message, typically used by automation

	 * developers to troubleshoot any errors that may occur

	 */

	 DEBUG,

	 /**

	 * Pass without Screenshot

	 */

	 PASSNS,

	 /**

	 * Fail without screenshot

	 */

	 FAILNS;

	
}
