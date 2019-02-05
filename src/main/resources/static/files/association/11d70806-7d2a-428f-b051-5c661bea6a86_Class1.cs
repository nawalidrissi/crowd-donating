using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp3
{
	class custwl
	{

		public string Wire_N;
		public string A_end;
		public string Cav_A;
		public string B_end;
		public string Cav_B;

		public custwl() { }

		public custwl(string _Wire_N, string _A_end, string _Cav_A , string _B_end , string _Cav_B)
		{

			this.Wire_N = _Wire_N;
			this.A_end = _A_end;
			this.Cav_A = _Cav_A;
			this.B_end = _B_end;
			this.Cav_B = _Cav_B;

		}


	}
}
