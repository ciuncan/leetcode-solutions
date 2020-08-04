package ciuncan.utils

enum DebugMode {
  case Off, On
}
object DebugMode {
  given offByDefault as DebugMode = DebugMode.Off
}

def [T: Debuggable] (t: T).tap(lbl: => String = "")(using debugMode: DebugMode): T = {
  if (debugMode == DebugMode.On) {
    val labelPart = if lbl.isEmpty then "" else s"$lbl: "
    println(labelPart + t.debug)
  }
  t
}

def [T, U: Debuggable] (t: T).tapF(lbl: => String, f: T => U)(using debugMode: DebugMode): T = {
  if (debugMode == DebugMode.On) {
    f(t).tap(lbl)
  }
  t
}
