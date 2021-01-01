(ns task.get-datasets
  "Convert holidays_detailed.yml to edn"
  (:require [clj-yaml.core :as yaml]))

(defn run []
  (as-> "holiday_jp/holidays_detailed.yml" v
    (slurp v)
    (yaml/parse-string v)
    (with-out-str (clojure.pprint/pprint v))
    (str "(ns holiday-jp.holidays)
          (def holidays " v ")")
    (spit "src/holiday_jp/holidays.cljc" v)))

(run)
