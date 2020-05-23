start java ServerHoroscopo.PublicadorServerHoroscopo 10003
start java ServerClima.PublicadorServerClima 10002
timeout 5
start java ServerCentral.PublicadorServerCentral localhost 10003 localhost 10002 10000
timeout 5
java Cliente.Test localhost 10000
