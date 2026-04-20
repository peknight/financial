package com.peknight.financial

import cats.{Applicative, Eq, Show}
import com.peknight.codec.Codec
import com.peknight.codec.config.given
import com.peknight.codec.cursor.Cursor
import com.peknight.codec.derivation.EnumCodecDerivation
import com.peknight.codec.sum.StringType

enum Offset derives CanEqual:
  case Open, Close, CloseToday
end Offset
object Offset:
  given stringCodecOffset[F[_]: Applicative]: Codec[F, String, String, Offset] =
    EnumCodecDerivation.unsafeDerivedStringCodecEnum[F, Offset]
  given codecOffsetS[F[_] : Applicative, S: {StringType, Show}]: Codec[F, S, Cursor[S], Offset] =
    Codec.codecS[F, S, Offset]
  given eqOffset: Eq[Offset] = Eq.fromUniversalEquals[Offset]
end Offset