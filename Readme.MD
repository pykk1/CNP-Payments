Java application which processes data: Personal Identification Number(CNP in romanian) separated from a pay amount(in RON) by a semicolon

Input data:
- Data comes from a .csv file, with semicolon separated fields.
- Every line in the file represents a payment.
- On a line, the first position is occupied by the CNP, and the second by the payment amount.
- Payment amount decimal separator is " . "
- There might be empty lines in the .csv file which should be ignored.

Output data:
- Average ayment amount.
- Small payments, the number of payments with a value smaller than 5000 units(including 5000 units).
- Big Payments, the number of payments with a value greater than 5000 units.
- Payments by minors
- Total amount of payments made by romanian citizens born in Bucharest.
- Total number of payments made by foreigners(CNP first digit = 9).
- Errors :
      - line(at which we got an error)
      - type :
               (0 : Invalid line.
               1 : Invalid CNP.
               2 : Invalid payment amount.)
              
Output data will be serialized in another .csv file.


![image](https://user-images.githubusercontent.com/80632633/113482062-8cf03800-94a5-11eb-8038-a4c013f2f1cd.png)
![image](https://user-images.githubusercontent.com/80632633/113482081-a85b4300-94a5-11eb-8034-fc61a8563a84.png)
