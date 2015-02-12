//
//  MITypeMacros.h
//  MappedIn
//
//  Created by Daniel Lichty on 2014-07-24.
//
//

#ifndef MITypeMacros_h
#define MITypeMacros_h

#define FORCE_TYPE(x, type) ([x isKindOfClass:[type class]] ? x : nil)

#define DICTIONARY(x)   FORCE_TYPE(x, NSDictionary)
#define ARRAY(x)        FORCE_TYPE(x, NSArray)
#define STRING(x)       FORCE_TYPE(x, NSString)
#define NUMBER(x)       FORCE_TYPE(x, NSNumber)

#endif
