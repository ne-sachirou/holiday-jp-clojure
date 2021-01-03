(ns holiday-jp
  "Japanese holiday."
  (:require [holiday-jp.holidays]))

(defn between
  "Holidays between the start and the last."
  [start last]
  #?(:clj (filter (fn [{date :date}] (and (>= (.compareTo date start) 0)
                                          (<= (.compareTo date last) 0)))
                  (vals holiday-jp.holidays/holidays))
     :clje (reverse (filter (fn [{date :date}] (and (>= (.timestamp date) (.timestamp start))
                                                    (<= (.timestamp date) (.timestamp last))))
                            (vals holiday-jp.holidays/holidays)))
     :cljr (filter (fn [{date :date}] (and (>= (.CompareTo date start) 0)
                                           (<= (.CompareTo date last) 0)))
                   (vals holiday-jp.holidays/holidays))
     :cljs (filter (fn [{date :date}] (and (>= (.getTime date) (.getTime start))
                                           (<= (.getTime date) (.getTime last))))
                   (vals holiday-jp.holidays/holidays))))

(defn holiday?
  "The date is a holiday or not."
  [date]
  (contains? holiday-jp.holidays/holidays date))

(defn on
  "When the date is holidays, return them.
   This returns a vector in case of a day have multiple holidays."
  [date]
  (if-let [holiday (get holiday-jp.holidays/holidays date)]
    [holiday]
    []))
