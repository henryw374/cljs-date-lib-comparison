# Time-lib Comparison

This comparison app was created to compare how various Clojurescript time libraries impact user experience
in a web application. The accompanying blog post can be [found here](https://widdindustries.com/clojurescript-datetime-lib-comparison/).

Disclaimer: I am the author of `cljc.java-time`, one of the libraries considered.

The application lets a user choose a future date and then informs them how many days there are 
between now and that date - so pretty simple.

There are two implementations, one using [Deja-Fu](https://github.com/lambdaisland/deja-fu) (which is based on the Javascript Date API)
and one using [cljc.java-time](https://github.com/henryw374/cljc.java-time) (which is a 1-1 API name-match with java.time)

The Deja-fu version has 2 deliberate bugs - as explained in the blog post linked above

* [Deja-Fu version](https://friendly-eats-demo-e71b7.web.app/js-date.html)
* [cljc.java-time version](https://friendly-eats-demo-e71b7.web.app/java-time.html)

These apps can be deployed on the web so that tools such as [PageSpeed](https://developers.google.com/speed/pagespeed/insights/)
can be used to test them. 

These apps are hosted on Firebase, but just because I already had a dummy project set up there. They don't
use any Firebase APIs.

## Dev

Run a clojure REPL from the top-level dir. 

This could be as simple as running `clj`,
but to get the most out of Clojure, you should be able to send commands to a REPL from
your editor. See [Practicalli](http://practicalli.github.io/clojure/clojure-editors/) for 
straightforward guides for many popular editors. 

Compile the `cljs` namespace and see the comment block at the bottom for some forms you might want to send in.

### Deploy to the web

Having done prod builds, deploy the contents of the firebase/public dir to the web however you like.
