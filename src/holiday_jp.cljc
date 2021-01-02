(ns holiday-jp
  "Japanese holiday."
  (:require [holiday-jp.holidays]))

(defn between
  "Holidays between start and last."
  [start last]
  (filter (fn [{date :date}] (and (or (= date start)
                                      (.after date start))
                                  (or (= date last)
                                      (.before date last))))
          (vals holiday-jp.holidays/holidays)))

(defn holiday?
  "The date is a holiday or not."
  [date]
  (contains? holiday-jp.holidays/holidays date))

(defn on
  "When the date is holidays, return them.
   This returns a list in case of a day have multiple holidays."
  [date]
  (if-let [holiday (get holiday-jp.holidays/holidays date)]
    [holiday]
    []))
