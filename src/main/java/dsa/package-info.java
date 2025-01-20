/**
 * <strong>The Data Structures and Algorithms Labs.</strong>
 * <p>
 * What to do at the start of a lab session (if using lab computers):
 * <ul>
 * <li>Open the Software Hub and launch:
 *     <ul>
 *     <li>Git for Windows</li>
 *     <li>IntelliJ Community Edition</li>
 *     </ul></li>
 * <li>Open PowerShell and run:
 *     <ul>
 *     <li>If lab 1:</li>
 *         <ul>
 *         <li>{@code N:}</li>
 *         <li>{@code git clone https://github.com/bertie-wheen/dsa-2024-5 dsa}</li>
 *         </ul></li>
 *     <li>Else (labs 2 onwards):
 *         <ul>
 *         <li>{@code cd N:/dsa}</li>
 *         <li>{@code git pull}</li>
 *         <li>If you get an error "fatal: detected dubious ownership in
 *             repository ..." (which you likely will):
 *             <ul>
 *             <li>{@code git config --global --add safe.directory '%(prefix)///smbhome.uscs.susx.ac.uk/<username>/dsa'}
 *                 (where {@code <username>} is your username)</li>
 *             <li>{@code git pull}</li>
 *             </ul></li>
 *         </ul></li>
 *     </ul></li>
 * <li>Open IntelliJ IDEA and:
 *     <ul>
 *     <li>Open {@code N:/dsa} as a project</li>
 *     <li>In the menu, select {@code Files -> Project Structure}</li>
 *     <li>Select {@code Project} in the left sidebar (if it isn't already
 *         selected)</li>
 *     <li>If there is no SDK:
 *         <ul>
 *         <li>Click on the dropdown, and select {@code Download JDK}</li>
 *         <li>Choose {@code Amazon Corretto} as the vendor, and click
 *             {@code Download}</li>
 *         <li>Click {@code Apply}, then {@code OK} to close the project
 *             structure dialog</li>
 *         </ul></li>
 *     </ul></li>
 * </ul>
 */
package dsa;
