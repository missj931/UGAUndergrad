#include	<iostream>
#include	<cstdlib>
using	namespace	std;

class	MyClass	{
				int	x;
		public:
				MyClass(int	val)	:	x(val)	{}
				const	int	&	get()	const	{	return	x;	}
				int	&	get()	{	return	x;	}
};

int	main()	{
		MyClass	foo(10);
		const	MyClass	bar(20);
		foo.get()	=	15;									//	ok:	get()	returns	int	& 
//	bar.get()	=	25;								//	not	valid:	get()	returns	const	int	& 
		cout	<<	foo.get()	<<	endl;
		cout	<<	bar.get()	<<	endl;
		return	EXIT_SUCCESS;
}	//	main
