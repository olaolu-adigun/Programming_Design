#include <iostream>
#include <string>
#include <sstream>
#include <vector>
using namespace std;

const char newline = '\n';

int main()
{
   vector<double> student_marks;
   double mark;
   char answer;
   
   cout<< "Enter marks (y/n)? "<<flush;
   cin >> answer;

   while (answer == 'y'|| answer == 'Y')
   {
     cout<< "Enter the value: "<<flush;
     cin >> mark;
     student_marks.push_back(mark);
     cout << "More students (y/n)? " <<flush;
     cin >> answer;
   }
    return 0;

}
