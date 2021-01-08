(ns task.test-cljr
  "Test for Clojure CLR"
  (:require [clojure.test]))

(load "../src/holiday_jp/holidays")
(load "../src/holiday_jp")
(load "../test/holiday_jp_test")
(clojure.test/run-tests 'holiday-jp-test)
