//bitwise Right Shift operator

class BitwiseRShift
{
  public static void main(String[] args)
  {
    int a=10,b;
    b=a>>2;
    System.out.println(a+">>2="+b);   
  }
}

/*	10/2=5	-0
	5/2=2	-1
	2/2=1	-0

	10		->	00001010
			->	--000010
			->	00000010
			->	2
-----------------------------------------------------------------------------------
        10		->	00001010
	1's		->	11110101
	2's		->	11110110
			->	--111101
			->	11111101
	
	11111101	->	1X2^7+1X2^6+1X2^5+1X2^4+1X2^3+1X2^2+1X2^0
			->	128+64+32+16+8+4+1
			->	253-256
			->	-3
*/