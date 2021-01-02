(ns holiday-jp
  "Japanese holiday."
  (:require [holiday-jp.holidays]))

(defn between
  "Holidays between the start and the last."
  [start last]
  #?(:clj (filter (fn [{date :date}] (and (or (= date start)
                                              (.after date start))
                                          (or (= date last)
                                              (.before date last))))
                  (vals holiday-jp.holidays/holidays))
     :clje (reverse (filter (fn [{date :date}] (and (>= (.timestamp date) (.timestamp start))
                                                    (<= (.timestamp date) (.timestamp last))))
                            (vals holiday-jp.holidays/holidays)))))

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
