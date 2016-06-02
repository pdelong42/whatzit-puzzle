(defproject whatzit-puzzle "0.1.0-SNAPSHOT"
   :license { :name "The MIT License (MIT)" }
   :dependencies [[org.clojure/clojure "1.7.0"]]
   :main ^:skip-aot whatzit-puzzle.core
   :target-path "target/%s"
   :profiles {:uberjar {:aot :all}}  )
