/**
 * The first thing to know about are types. The available types in Thrift are:
 *
 *  bool        Boolean, one byte
 *  byte        Signed byte
 *  i16         Signed 16-bit integer
 *  i32         Signed 32-bit integer
 *  i64         Signed 64-bit integer
 *  double      64-bit floating point value
 *  string      String
 *  binary      Blob (byte array)
 *  map<t1,t2>  Map from one type to another
 *  list<t1>    Ordered list of one type
 *  set<t1>     Set of unique elements of one type
 *
 * Did you also notice that Thrift supports C style comments?
 */


/**
 * Thrift files can namespace, package, or prefix their output in various
 * target languages.
 */
namespace cpp dependrpc.server
namespace java org.tariel.dependrpc.server
//namespace java models.client
namespace php dependrpc.server


exception SystemError {
  1: i32 what,
  2: string why
}

exception ClientError {
  1: i32 what,
  2: string why
}

service ParseServer {
	
	list<string> getServerInfo(),

	list<string> ParseText(1: string language, 2: string text),

}