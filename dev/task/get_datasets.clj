(ns task.get-datasets
  "Convert holidays_detailed.yml to edn"
  (:require
    [clj-yaml.core :as yaml])
  (:import
    (java.util
      Calendar
      TimeZone)))


(defn as-jst
  [date]
  (let [cal (Calendar/getInstance (TimeZone/getTimeZone "Asia/Tokyo"))]
    (.setTime cal date)
    (.set cal Calendar/HOUR_OF_DAY 0)
    cal))


(defn run
  []
  (as-> "holiday_jp/holidays_detailed.yml" x
        (slurp x)
        (yaml/parse-string x)
        (into (sorted-map) (map (fn [[k v]] [(as-jst k) (update v :date as-jst)]) x))
        (with-out-str (clojure.pprint/pprint x))
        (str "(ns holiday-jp.holidays)
          (def holidays " x ")")
        (spit "src/holiday_jp/holidays.cljc" x)))


(run)
