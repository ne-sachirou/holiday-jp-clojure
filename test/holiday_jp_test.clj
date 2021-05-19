(ns holiday-jp-test
  (:require
    [clojure.test :refer [deftest is testing]]
    [holiday-jp]))


(deftest between-test
  (testing "When the start and the last aren't a holiday."
    (is (= [{:date #inst "2019-11-23T00:00:00.000+09:00",
             :week "土",
             :week_en "Saturday",
             :name "勤労感謝の日",
             :name_en "Labor Thanksgiving Day"},
            {:date #inst "2020-01-01T00:00:00.000+09:00",
             :week "水",
             :week_en "Wednesday",
             :name "元日",
             :name_en "New Year's Day"}]
           (holiday-jp/between #inst"2019-11-22T00:00:00+09:00" #inst"2020-01-02T00:00:00+09:00"))))
  (testing "When the start is a holiday but the last isn't."
    (is (= [{:date #inst "2019-11-23T00:00:00.000+09:00",
             :week "土",
             :week_en "Saturday",
             :name "勤労感謝の日",
             :name_en "Labor Thanksgiving Day"},
            {:date #inst "2020-01-01T00:00:00.000+09:00",
             :week "水",
             :week_en "Wednesday",
             :name "元日",
             :name_en "New Year's Day"}]
           (holiday-jp/between #inst"2019-11-23T00:00:00+09:00" #inst"2020-01-02T00:00:00+09:00"))))
  (testing "When the start isn't a holiday but the last is."
    (is (= [{:date #inst "2019-11-23T00:00:00.000+09:00",
             :week "土",
             :week_en "Saturday",
             :name "勤労感謝の日",
             :name_en "Labor Thanksgiving Day"},
            {:date #inst "2020-01-01T00:00:00.000+09:00",
             :week "水",
             :week_en "Wednesday",
             :name "元日",
             :name_en "New Year's Day"}]
           (holiday-jp/between #inst"2019-11-22T00:00:00+09:00" #inst"2020-01-01T00:00:00+09:00"))))
  (testing "When the start and the last are holidays."
    (is (= [{:date #inst "2019-11-23T00:00:00.000+09:00",
             :week "土",
             :week_en "Saturday",
             :name "勤労感謝の日",
             :name_en "Labor Thanksgiving Day"},
            {:date #inst "2020-01-01T00:00:00.000+09:00",
             :week "水",
             :week_en "Wednesday",
             :name "元日",
             :name_en "New Year's Day"}]
           (holiday-jp/between #inst"2019-11-23T00:00:00+09:00" #inst"2020-01-01T00:00:00+09:00"))))
  (testing "When there's no holiday between the start and the last."
    (is (= []
           (holiday-jp/between #inst"2019-11-24T00:00:00+09:00" #inst"2019-12-31T00:00:00+09:00"))))
  (testing "When the start and the last are same."
    (is (= [{:date #inst"2020-01-01T00:00:00.000+09:00",
             :week "水",
             :week_en "Wednesday",
             :name "元日",
             :name_en "New Year's Day"}]
           (holiday-jp/between #inst"2020-01-01T00:00:00+09:00" #inst"2020-01-01T00:00:00+09:00")))))


(deftest holidays?-test
  (testing "When the date is a holiday."
    (is (holiday-jp/holiday? #inst"2019-11-23T00:00:00+09:00")))
  (testing "When the date isn't a holiday."
    (is (not (holiday-jp/holiday? #inst"2019-11-22T00:00:00+09:00")))))


(deftest on-test
  (testing "When the date is a holiday."
    (is (= [{:date #inst "2019-11-23T00:00:00.000+09:00",
             :week "土",
             :week_en "Saturday",
             :name "勤労感謝の日",
             :name_en "Labor Thanksgiving Day"}]
           (holiday-jp/on #inst"2019-11-23T00:00:00+09:00"))))
  (testing "When the date isn't a holiday."
    (is (= []
           (holiday-jp/on #inst"2019-11-22T00:00:00+09:00")))))
