(defproject whatzit-puzzle "0.1.0-SNAPSHOT"
   :license { :name "The MIT License (MIT)" }
   :dependencies [
      [net.mikera/core.matrix        "0.52.0"]
      [org.clojure/clojure            "1.8.0"]
      [org.clojure/math.combinatorics "0.1.3"]  ]
   :main ^:skip-aot whatzit-puzzle.core
   :target-path "target/%s"
   :profiles {:uberjar {:aot :all}}  )

