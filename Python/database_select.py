import mysql.connector
from mysql.connector import errorcode

try:
    conn = mysql.connector.connect(user='root', password='root', host='127.0.0.1', database='happylifedb')
    cursor = conn.cursor()
    query = ("SELECT email_address, full_name FROM user "
         "WHERE user_id=%s")
    cursor.execute(query, (12,))

    for (email_address, full_name) in cursor:
        print ("{}, {}".format(email_address, full_name))
    cursor.close()
    conn.close()

except mysql.connector.Error as err:
  if err.errno == errorcode.ER_ACCESS_DENIED_ERROR:
    print("Something is wrong with your user name or password")
  elif err.errno == errorcode.ER_BAD_DB_ERROR:
    print("Database does not exist")
  else:
    print(err)
else:
    conn.close()