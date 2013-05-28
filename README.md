# mailcheck

[![Build Status](https://travis-ci.org/KushalP/mailcheck-clj.png?branch=master)](https://travis-ci.org/KushalP/mailcheck-clj)

A Clojure translation of the
[Kicksend mailcheck](https://github.com/Kicksend/mailcheck) javascript
library which suggests a right domain when your users misspell it in
an email address.

When your user types in "user@hotnail.com", Mailcheck will suggest "user@hotmail.com".

## Usage

Add the following to your list of dependencies:

```clojure
[mailcheck "0.1.1"]
```

Then you can use it in a REPL as follows:

```clojure
(use 'mailcheck.core)
(suggest "user@hotnail.com")
=> {:address "user", :domain "hotmail.com", :full "user@hotmail.com"}
```

## License

Copyright © 2013 Kushal Pisavadia

Distributed under the Eclipse Public License, the same as Clojure.

### Credits

[Mailcheck.js](https://github.com/Kicksend/mailcheck): the original idea
