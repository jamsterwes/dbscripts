import random

random.seed(0xDEADBEEF)

with open("employees.txt") as employee_file:
    employees = [e.strip() for e in employee_file.readlines()]

print(employees)