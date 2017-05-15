
listt = [5,6,7]

tupFromList = tuple(listt);

print(tupFromList);

numericalTuple = (1,2,3,99,97,55,44,11);
alphabetTuple = ('a','b','c','x','y','z','s','t');
nestedTuple = ("nums",[1,2,3], (7,8,9))
singleElmTuple = ("single",)

# print elements 3rd to 5th
print(numericalTuple[2:5])

print ("length: ",len(singleElmTuple))

print("Min: ",min(numericalTuple))

print("Max: ", max(numericalTuple))

print ("Sum: ",sum(tupFromList))

print ("Nested tup: ",nestedTuple)

print(numericalTuple+alphabetTuple)

#del numericalTuple;  # deletes the tuple. Makes it undefined.

print (numericalTuple[0])

print (numericalTuple[-8])