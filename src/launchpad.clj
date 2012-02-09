(ns launchpad
  (:use overtone.midi))

(defn init-launchpad
  "Intiatialize the Novation Launchpad.
   {:display [8x8]
    :buttonmap [80]
    :onpress-cb {}
    :onrelease-cb {}
    :midi-in ...
    :midi-out ...
    }"
  []
  (
   ))

(def launchpad-out (midi-out "Launchpad"))

(defn draw
  ([lp x y r g]
     (draw lp x y (+ (* 16 g) r)))
  ([lp x y c]
     (draw lp (+ (* 16 y) x) c))
  ([lp p c]
     (midi-note-on lp p c)))

(defn colormap
  []
  (doseq [x (range 0 4) y (range 0 4)]
    (midi-note-on launchpad-out (+ (* y 16) x) (+ (* y 16) x))))

(defn randomize [lp n]
  (loop [n n]
    (when (> n 0)
      (let [x (rand-int 8)
            y (rand-int 8)
            r (rand-int 4)
            g (rand-int 4)]
        (draw lp x y r g)
        (recur (dec n))))))

(defn input-handler [msg timestamp]
  (println msg " <" timestamp ">"))
