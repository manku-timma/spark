A simple spark streaming example. It monitors the network port 9999
and prints the line count and word count from that line stream. Use `nc -lk
127.0.0.1 9999` as the source. It also keeps track of all the lines and
words seen and their respective counts.
