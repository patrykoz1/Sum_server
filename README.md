# Sum_server
Basic sum server 
****************************************************************
author:Patryk Zur
e-mail:patrykoz1@wp.pl
date:March,2019
****************************************************************
Description:
This is basic sum server. Client sends lines with numbers, and
server returns sum of them. Originally, server was written on Linux.
If you have other OS (or other distribution) you have to change line no.91 
to "\\n" instead "\\r\\n".It's caused by different ways to terminate line.

How to start?
Just compile using javac "name".java, and then start using java "name".
In other terminal send few lines from file to the server. Just type:

"ncat 127.0.0.1 2019 < test-data.txt > server-response.txt"

These files and our server should be in the same director.
You can get warning about deprecated method - it depends what standard you use.

Enjoy!
