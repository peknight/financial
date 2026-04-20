package com.peknight.financial

import cats.syntax.eq.*
import cats.{Applicative, Eq, Show}
import com.peknight.codec.Codec
import com.peknight.codec.cursor.Cursor
import com.peknight.codec.sum.StringType

case class Symbol(value: String):
  override def toString: String = value
end Symbol
object Symbol:
  given stringCodecSymbol[F[_] : Applicative]: Codec[F, String, String, Symbol] =
    Codec.map[F, String, String, Symbol](_.value)(Symbol.apply)
  given codecSymbol[F[_] : Applicative, S: {StringType, Show}]: Codec[F, S, Cursor[S], Symbol] =
    Codec.codecS[F, S, Symbol]
  given eqSymbol: Eq[Symbol] with
    def eqv(x: Symbol, y: Symbol): Boolean = x.value === y.value
  end eqSymbol
end Symbol
