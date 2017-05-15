

newFile = open("abc.txt", "w", -1, encoding = 'utf-8')		# -1 is default system buffering. w specifies open,overwrite,create


newFile.write("abcdefghijklmnopqrstuvwxyz\n")
newFile.write("abcdefghijklmnopqrstuvwxyz\n\n\n")
newFile.write("abcdefghijklmnopqrstuvwxyz\n")

newFile.close()


createdFile = open("abc.txt", "r", encoding = 'utf-8');


print (createdFile.read(5))

print (createdFile.read())

createdFile.seek(0)

print (createdFile.readlines())

createdFile.seek(0)

print (createdFile.readline(-1))