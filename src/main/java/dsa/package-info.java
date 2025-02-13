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
 * These labs are structured into
 * <a href="https://docs.oracle.com/javase/specs/jls/se8/html/jls-7.html">packages</a>,
 * one package per lab (e.g. {@link dsa.lab01} for lab 1). All are subpackages
 * of the top-level {@link dsa} package, and each is further structured into
 * subpackages.
 * <p>
 * Each lab contains an {@code exercises} subpackage (e.g.
 * {@link dsa.lab01.exercises}) that contains one or more exercises. Each
 * exercise is a partially-implemented
 * <a href="https://docs.oracle.com/javase/specs/jls/se8/html/jls-8.html">class</a>
 * with one or more {@code TODO}s that you are expected to fill in in order to
 * complete the implementation.
 * <p>
 * Model solutions for each of the exercises are provided in the
 * {@code solutions} subpackage (e.g. {@link dsa.lab01.solutions}).
 * <p>
 * Some labs also include a {@code base} subpackage that contains given code
 * that is needed for the exercises, and thus - though these files aren't
 * exercises and you don't need to edit them - you should have a look through
 * them before starting the exercises.
 * <p>
 * You often don't need to worry too much about their actual code - the most
 * important thing is to look at their type signatures: For example, if it's a
 * class, what you should look for are what methods it has, what parameters they
 * take, what they're supposed to do and what they return.
 * <p>
 * The most commonly-provided {@code base} code are interfaces defining abstract
 * data types (such as in {@link dsa.lab01.base.StringContainer}, as exercises
 * will often be to implement various concrete data types as subclasses of these
 * ADTs (e.g. as in {@link dsa.lab01.exercises.StringArray}).
 * <ul>
 * <li>{@linkplain dsa.lab01 Lab 1}</li>
 * <li>{@linkplain dsa.lab02 Lab 2}</li>
 * <li>{@linkplain dsa.lab03 Lab 3}</li>
 * <li>{@linkplain dsa.lab04 Lab 4}</li>
 * </ul>
 */
package dsa;
