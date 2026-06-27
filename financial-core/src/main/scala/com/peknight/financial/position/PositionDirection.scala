package com.peknight.financial.position

import cats.{Applicative, Eq, Show}
import com.peknight.codec.Codec
import com.peknight.codec.config.given
import com.peknight.codec.cursor.Cursor
import com.peknight.codec.derivation.EnumCodecDerivation
import com.peknight.codec.sum.StringType
import com.peknight.financial.Direction

enum PositionDirection extends Direction:
  case Long, Short
end PositionDirection
object PositionDirection:
  given stringCodecPositionDirection[F[_]: Applicative]: Codec[F, String, String, PositionDirection] =
    EnumCodecDerivation.unsafeDerivedStringCodecEnum[F, PositionDirection]
  given codecPositionDirectionS[F[_] : Applicative, S: {StringType, Show}]: Codec[F, S, Cursor[S], PositionDirection] =
    Codec.codecS[F, S, PositionDirection]
  given eqPositionDirection: Eq[PositionDirection] = Eq.fromUniversalEquals[PositionDirection]
end PositionDirection
