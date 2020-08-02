package ciuncan.utils

enum DebugMode:
  case Off
  case On
object DebugMode:
  given offByDefault as DebugMode = DebugMode.Off
end DebugMode

def [T: Debuggable] (t: T).tap(lbl: => String = "")(using debugMode: DebugMode): T =
  if debugMode == DebugMode.On then
    val labelPart = if lbl.isEmpty then "" else s"$lbl: "
    println(labelPart + t.debug)
  t

def [T, U: Debuggable] (t: T).tapF(lbl: => String, f: T => U)(using debugMode: DebugMode): T =
  if debugMode == DebugMode.On then
    f(t).tap(lbl)
  t
