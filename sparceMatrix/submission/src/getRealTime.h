/*
 * Author:  David Robert Nadeau
 * Site:    http://NadeauSoftware.com/
 * License: Creative Commons Attribution 3.0 Unported License
 *          http://creativecommons.org/licenses/by/3.0/deed.en_US
 */

#ifndef getRealTime_h
#define getRealTime_h

/*
Returns an aboslute monotonic time. Because different platforms support different
timing calls, this code uniformizes the handling across machines and makes 
the executable essentially protable between Windows, Linux and Mac platforms. 
The final executable does not need to rely on this if the platform is known,
or if timing information is not required, 
so there would be no issue with licensing.
*/
double getRealTime();

#endif /* getRealTime_h */
