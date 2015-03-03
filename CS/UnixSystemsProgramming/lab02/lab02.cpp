#include <iostream>
#include <cstdlib>

void increment(int i, int * pim int & ri);

int main(){

	int x = 10;
	x++;
	
	const int c = 200;

	const int * p1c = &c;
	p1c++;
	(*p1c)++;
	
	int const * p2c = &c;
	p2c++;
	(*p2c)++;

	char ch = 'a';
	char * const cpc = &ch;
	cpc++;
	(*cpc)++;

	return EXIT_SUCCESS;

}//main
	










/*

//void increment (int i, int * pi, int & ri);
//using namespace std;

//using std::cout;
//using std::endl;


using namespace std;

//void increment(int i, int * pi, int & ri);


int main() {


	int a = 1, b = 2, c = 3;

	cout << a << " " << b << " " << c << endl;
	increment(a, &b, c);
	cout << a << " " << b << " " << c << endl;

} //main

	void increment( int i, int * pi, int & ri){

		i = i + 1;
		*pi = *pi + 1;
		ri = ri + 1;
		cout << i << " " << *pi << " " << ri << endl;

	} //increment



//} // main



**/




	/*

	int x = 10;

	int * px = &x;

	int a[10] = {100, 100, 100, 100, 100, 100, 100, 100, 100, 100};
	int b[5] = {1000, 1000, 1000, 1000, 1000};

	int * p1 = a;
	int * p2 = b;

	int (*parray)[10] = &a; // line 17
	//int (*parray) [10] = &b;
	//int (*parray) [] = &b;

	

	int * ap1[3];
	ap1[0] = &x;
	ap1[1] = p1;
	ap1[2] = b;

	int (*ap2[2])[10];
	//ap2[0] = &a;
	ap2[0] = &(*parray);

	ap2[1] = parray;

	return EXIT_SUCCESS;

} //main

**/



/*
	int array [10], * p1, * p2;
	p1 = array;
	p2 = &array[0];

	//for (int i = 0; i < 10; i++) array [i] = i; 
	for(int i = 0; i < 10; i++) *(p1++) = i;



	for (int i = 0; i < 10; i++) cout << *(p2 + i) << endl;

	return EXIT_SUCCESS;

	}//main

**/
/*
	int *p1;
	char *p2;
	p2 = p1;
	return EXIT_SUCCESS;
**/





/*
	int integer1, *p1, **p2;

	integer1 = 10; 	// line 11
	p1 = &integer1; // line 12
	p2 = &p1;	// line 13
	
	*p1++;
	cout << "integer1 = " << integer1 << endl;

	//integer1++;
	//cout << "integer1 = " << *p1 << endl;
	//(*p1)++;
	//cout << "integer1 = " << *p1 << endl;
	//cout << "integer1 = " << integer1 << endl; 	//line 15
	cout << "p1 = " << p1 << endl;			//line 16
	cout << "p2 = " << p2 << endl;			//line 17

	return EXIT_SUCCESS;
**/

//} // main


