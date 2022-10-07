// Generated from D:/STUDY/COM/MxStar-Compiler-Design-2022/src/Parser\Mxstar.g4 by ANTLR 4.10.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MxstarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.10.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, Add=2, Sub=3, Mul=4, Div=5, Mod=6, Less=7, LessEqual=8, Greater=9, 
		GreaterEqual=10, EqualEqual=11, NotEqual=12, AndAnd=13, OrOr=14, Not=15, 
		LShift=16, Rshift=17, And=18, Or=19, Caret=20, Tilde=21, Assign=22, AddAdd=23, 
		SubSub=24, Dot=25, LParen=26, RParen=27, LBrack=28, RBrack=29, LBrace=30, 
		RBrace=31, Question=32, Colon=33, Semi=34, Comma=35, Void=36, Bool=37, 
		Int=38, String=39, New=40, Class=41, Null=42, True=43, False=44, This=45, 
		If=46, Else=47, For=48, While=49, Break=50, Continue=51, Return=52, Arrow=53, 
		Whitespace=54, Newline=55, LineComment=56, BlockComment=57, Identifier=58, 
		IntLiteral=59, StringLiteral=60;
	public static final int
		RULE_program = 0, RULE_mainFunction = 1, RULE_primaryExpression = 2, RULE_literal = 3, 
		RULE_funCall = 4, RULE_arrayParameter = 5, RULE_newArray = 6, RULE_newClass = 7, 
		RULE_lambdaExpression = 8, RULE_lambdaIntroducer = 9, RULE_selfExpression = 10, 
		RULE_unaryExpression = 11, RULE_multiplicativeExpression = 12, RULE_additiveExpression = 13, 
		RULE_shiftExpression = 14, RULE_relationalExpression = 15, RULE_equalityExpression = 16, 
		RULE_andExpression = 17, RULE_exclusiveOrExpression = 18, RULE_inclusiveOrExpression = 19, 
		RULE_logicalAndExpression = 20, RULE_logicalOrExpression = 21, RULE_assignmentExpression = 22, 
		RULE_expression = 23, RULE_statement = 24, RULE_expressionStatement = 25, 
		RULE_compoundStatement = 26, RULE_statementSeq = 27, RULE_selectionStatement = 28, 
		RULE_condition = 29, RULE_iterationStatement = 30, RULE_forInitStatement = 31, 
		RULE_jumpStatement = 32, RULE_varDeclaration = 33, RULE_declarationElement = 34, 
		RULE_funcDefinition = 35, RULE_funcParameter = 36, RULE_funcParameterList = 37, 
		RULE_classDefinition = 38, RULE_classConstruction = 39, RULE_typename = 40;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "mainFunction", "primaryExpression", "literal", "funCall", 
			"arrayParameter", "newArray", "newClass", "lambdaExpression", "lambdaIntroducer", 
			"selfExpression", "unaryExpression", "multiplicativeExpression", "additiveExpression", 
			"shiftExpression", "relationalExpression", "equalityExpression", "andExpression", 
			"exclusiveOrExpression", "inclusiveOrExpression", "logicalAndExpression", 
			"logicalOrExpression", "assignmentExpression", "expression", "statement", 
			"expressionStatement", "compoundStatement", "statementSeq", "selectionStatement", 
			"condition", "iterationStatement", "forInitStatement", "jumpStatement", 
			"varDeclaration", "declarationElement", "funcDefinition", "funcParameter", 
			"funcParameterList", "classDefinition", "classConstruction", "typename"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'main'", "'+'", "'-'", "'*'", "'/'", "'%'", "'<'", "'<='", "'>'", 
			"'>='", "'=='", "'!='", "'&&'", "'||'", "'!'", "'<<'", "'>>'", "'&'", 
			"'|'", "'^'", "'~'", "'='", "'++'", "'--'", "'.'", "'('", "')'", "'['", 
			"']'", "'{'", "'}'", "'?'", "':'", "';'", "','", "'void'", "'bool'", 
			"'int'", "'string'", "'new'", "'class'", "'null'", "'true'", "'false'", 
			"'this'", "'if'", "'else'", "'for'", "'while'", "'break'", "'continue'", 
			"'return'", "'->'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, "Add", "Sub", "Mul", "Div", "Mod", "Less", "LessEqual", "Greater", 
			"GreaterEqual", "EqualEqual", "NotEqual", "AndAnd", "OrOr", "Not", "LShift", 
			"Rshift", "And", "Or", "Caret", "Tilde", "Assign", "AddAdd", "SubSub", 
			"Dot", "LParen", "RParen", "LBrack", "RBrack", "LBrace", "RBrace", "Question", 
			"Colon", "Semi", "Comma", "Void", "Bool", "Int", "String", "New", "Class", 
			"Null", "True", "False", "This", "If", "Else", "For", "While", "Break", 
			"Continue", "Return", "Arrow", "Whitespace", "Newline", "LineComment", 
			"BlockComment", "Identifier", "IntLiteral", "StringLiteral"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Mxstar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MxstarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramContext extends ParserRuleContext {
		public MainFunctionContext mainFunction() {
			return getRuleContext(MainFunctionContext.class,0);
		}
		public TerminalNode EOF() { return getToken(MxstarParser.EOF, 0); }
		public List<VarDeclarationContext> varDeclaration() {
			return getRuleContexts(VarDeclarationContext.class);
		}
		public VarDeclarationContext varDeclaration(int i) {
			return getRuleContext(VarDeclarationContext.class,i);
		}
		public List<ClassDefinitionContext> classDefinition() {
			return getRuleContexts(ClassDefinitionContext.class);
		}
		public ClassDefinitionContext classDefinition(int i) {
			return getRuleContext(ClassDefinitionContext.class,i);
		}
		public List<FuncDefinitionContext> funcDefinition() {
			return getRuleContexts(FuncDefinitionContext.class);
		}
		public FuncDefinitionContext funcDefinition(int i) {
			return getRuleContext(FuncDefinitionContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(85);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
					case 1:
						{
						setState(82);
						varDeclaration();
						}
						break;
					case 2:
						{
						setState(83);
						classDefinition();
						}
						break;
					case 3:
						{
						setState(84);
						funcDefinition();
						}
						break;
					}
					} 
				}
				setState(89);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			setState(90);
			mainFunction();
			setState(96);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Void) | (1L << Bool) | (1L << Int) | (1L << String) | (1L << Class) | (1L << Identifier))) != 0)) {
				{
				setState(94);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
				case 1:
					{
					setState(91);
					varDeclaration();
					}
					break;
				case 2:
					{
					setState(92);
					classDefinition();
					}
					break;
				case 3:
					{
					setState(93);
					funcDefinition();
					}
					break;
				}
				}
				setState(98);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(99);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MainFunctionContext extends ParserRuleContext {
		public TerminalNode Int() { return getToken(MxstarParser.Int, 0); }
		public TerminalNode LParen() { return getToken(MxstarParser.LParen, 0); }
		public TerminalNode RParen() { return getToken(MxstarParser.RParen, 0); }
		public CompoundStatementContext compoundStatement() {
			return getRuleContext(CompoundStatementContext.class,0);
		}
		public MainFunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mainFunction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterMainFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitMainFunction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitMainFunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MainFunctionContext mainFunction() throws RecognitionException {
		MainFunctionContext _localctx = new MainFunctionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_mainFunction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(101);
			match(Int);
			setState(102);
			match(T__0);
			setState(103);
			match(LParen);
			setState(104);
			match(RParen);
			setState(105);
			compoundStatement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrimaryExpressionContext extends ParserRuleContext {
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public TerminalNode This() { return getToken(MxstarParser.This, 0); }
		public TerminalNode Null() { return getToken(MxstarParser.Null, 0); }
		public TerminalNode Identifier() { return getToken(MxstarParser.Identifier, 0); }
		public FunCallContext funCall() {
			return getRuleContext(FunCallContext.class,0);
		}
		public ArrayParameterContext arrayParameter() {
			return getRuleContext(ArrayParameterContext.class,0);
		}
		public TerminalNode LParen() { return getToken(MxstarParser.LParen, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RParen() { return getToken(MxstarParser.RParen, 0); }
		public LambdaExpressionContext lambdaExpression() {
			return getRuleContext(LambdaExpressionContext.class,0);
		}
		public NewArrayContext newArray() {
			return getRuleContext(NewArrayContext.class,0);
		}
		public NewClassContext newClass() {
			return getRuleContext(NewClassContext.class,0);
		}
		public PrimaryExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primaryExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterPrimaryExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitPrimaryExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitPrimaryExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimaryExpressionContext primaryExpression() throws RecognitionException {
		PrimaryExpressionContext _localctx = new PrimaryExpressionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_primaryExpression);
		try {
			setState(122);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(107);
				literal();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(108);
				match(This);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(109);
				match(Null);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(110);
				match(Identifier);
				setState(113);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case LParen:
					{
					setState(111);
					funCall();
					}
					break;
				case LBrack:
					{
					setState(112);
					arrayParameter();
					}
					break;
				case Add:
				case Sub:
				case Mul:
				case Div:
				case Mod:
				case Less:
				case LessEqual:
				case Greater:
				case GreaterEqual:
				case EqualEqual:
				case NotEqual:
				case AndAnd:
				case OrOr:
				case LShift:
				case Rshift:
				case And:
				case Or:
				case Caret:
				case Assign:
				case AddAdd:
				case SubSub:
				case Dot:
				case RParen:
				case RBrack:
				case Semi:
				case Comma:
					break;
				default:
					break;
				}
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(115);
				match(LParen);
				setState(116);
				expression();
				setState(117);
				match(RParen);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(119);
				lambdaExpression();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(120);
				newArray();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(121);
				newClass();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LiteralContext extends ParserRuleContext {
		public TerminalNode IntLiteral() { return getToken(MxstarParser.IntLiteral, 0); }
		public TerminalNode StringLiteral() { return getToken(MxstarParser.StringLiteral, 0); }
		public TerminalNode False() { return getToken(MxstarParser.False, 0); }
		public TerminalNode True() { return getToken(MxstarParser.True, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << True) | (1L << False) | (1L << IntLiteral) | (1L << StringLiteral))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunCallContext extends ParserRuleContext {
		public TerminalNode LParen() { return getToken(MxstarParser.LParen, 0); }
		public TerminalNode RParen() { return getToken(MxstarParser.RParen, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(MxstarParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(MxstarParser.Comma, i);
		}
		public FunCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterFunCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitFunCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitFunCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunCallContext funCall() throws RecognitionException {
		FunCallContext _localctx = new FunCallContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_funCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(126);
			match(LParen);
			setState(135);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Add) | (1L << Sub) | (1L << Not) | (1L << Tilde) | (1L << AddAdd) | (1L << SubSub) | (1L << LParen) | (1L << LBrack) | (1L << New) | (1L << Null) | (1L << True) | (1L << False) | (1L << This) | (1L << Identifier) | (1L << IntLiteral) | (1L << StringLiteral))) != 0)) {
				{
				setState(127);
				expression();
				setState(132);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Comma) {
					{
					{
					setState(128);
					match(Comma);
					setState(129);
					expression();
					}
					}
					setState(134);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(137);
			match(RParen);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayParameterContext extends ParserRuleContext {
		public List<TerminalNode> LBrack() { return getTokens(MxstarParser.LBrack); }
		public TerminalNode LBrack(int i) {
			return getToken(MxstarParser.LBrack, i);
		}
		public List<TerminalNode> RBrack() { return getTokens(MxstarParser.RBrack); }
		public TerminalNode RBrack(int i) {
			return getToken(MxstarParser.RBrack, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ArrayParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayParameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterArrayParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitArrayParameter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitArrayParameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayParameterContext arrayParameter() throws RecognitionException {
		ArrayParameterContext _localctx = new ArrayParameterContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_arrayParameter);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(144); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(139);
					match(LBrack);
					setState(141);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Add) | (1L << Sub) | (1L << Not) | (1L << Tilde) | (1L << AddAdd) | (1L << SubSub) | (1L << LParen) | (1L << LBrack) | (1L << New) | (1L << Null) | (1L << True) | (1L << False) | (1L << This) | (1L << Identifier) | (1L << IntLiteral) | (1L << StringLiteral))) != 0)) {
						{
						setState(140);
						expression();
						}
					}

					setState(143);
					match(RBrack);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(146); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NewArrayContext extends ParserRuleContext {
		public TerminalNode New() { return getToken(MxstarParser.New, 0); }
		public TypenameContext typename() {
			return getRuleContext(TypenameContext.class,0);
		}
		public ArrayParameterContext arrayParameter() {
			return getRuleContext(ArrayParameterContext.class,0);
		}
		public NewArrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_newArray; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterNewArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitNewArray(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitNewArray(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NewArrayContext newArray() throws RecognitionException {
		NewArrayContext _localctx = new NewArrayContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_newArray);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(148);
			match(New);
			setState(149);
			typename();
			setState(150);
			arrayParameter();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NewClassContext extends ParserRuleContext {
		public TerminalNode New() { return getToken(MxstarParser.New, 0); }
		public TerminalNode Identifier() { return getToken(MxstarParser.Identifier, 0); }
		public TerminalNode LParen() { return getToken(MxstarParser.LParen, 0); }
		public TerminalNode RParen() { return getToken(MxstarParser.RParen, 0); }
		public NewClassContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_newClass; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterNewClass(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitNewClass(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitNewClass(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NewClassContext newClass() throws RecognitionException {
		NewClassContext _localctx = new NewClassContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_newClass);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(152);
			match(New);
			setState(153);
			match(Identifier);
			setState(156);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LParen) {
				{
				setState(154);
				match(LParen);
				setState(155);
				match(RParen);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LambdaExpressionContext extends ParserRuleContext {
		public LambdaIntroducerContext lambdaIntroducer() {
			return getRuleContext(LambdaIntroducerContext.class,0);
		}
		public FuncParameterContext funcParameter() {
			return getRuleContext(FuncParameterContext.class,0);
		}
		public TerminalNode Arrow() { return getToken(MxstarParser.Arrow, 0); }
		public CompoundStatementContext compoundStatement() {
			return getRuleContext(CompoundStatementContext.class,0);
		}
		public FunCallContext funCall() {
			return getRuleContext(FunCallContext.class,0);
		}
		public LambdaExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lambdaExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterLambdaExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitLambdaExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitLambdaExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LambdaExpressionContext lambdaExpression() throws RecognitionException {
		LambdaExpressionContext _localctx = new LambdaExpressionContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_lambdaExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(158);
			lambdaIntroducer();
			setState(159);
			funcParameter();
			setState(160);
			match(Arrow);
			setState(161);
			compoundStatement();
			setState(162);
			funCall();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LambdaIntroducerContext extends ParserRuleContext {
		public TerminalNode LBrack() { return getToken(MxstarParser.LBrack, 0); }
		public TerminalNode RBrack() { return getToken(MxstarParser.RBrack, 0); }
		public TerminalNode And() { return getToken(MxstarParser.And, 0); }
		public LambdaIntroducerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lambdaIntroducer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterLambdaIntroducer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitLambdaIntroducer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitLambdaIntroducer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LambdaIntroducerContext lambdaIntroducer() throws RecognitionException {
		LambdaIntroducerContext _localctx = new LambdaIntroducerContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_lambdaIntroducer);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(164);
			match(LBrack);
			setState(166);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==And) {
				{
				setState(165);
				match(And);
				}
			}

			setState(168);
			match(RBrack);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SelfExpressionContext extends ParserRuleContext {
		public PrimaryExpressionContext primaryExpression() {
			return getRuleContext(PrimaryExpressionContext.class,0);
		}
		public TerminalNode AddAdd() { return getToken(MxstarParser.AddAdd, 0); }
		public TerminalNode SubSub() { return getToken(MxstarParser.SubSub, 0); }
		public TerminalNode Dot() { return getToken(MxstarParser.Dot, 0); }
		public SelfExpressionContext selfExpression() {
			return getRuleContext(SelfExpressionContext.class,0);
		}
		public SelfExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selfExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterSelfExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitSelfExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitSelfExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelfExpressionContext selfExpression() throws RecognitionException {
		SelfExpressionContext _localctx = new SelfExpressionContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_selfExpression);
		try {
			setState(178);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(170);
				primaryExpression();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(171);
				primaryExpression();
				setState(176);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case AddAdd:
					{
					setState(172);
					match(AddAdd);
					}
					break;
				case SubSub:
					{
					setState(173);
					match(SubSub);
					}
					break;
				case Dot:
					{
					setState(174);
					match(Dot);
					setState(175);
					selfExpression();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnaryExpressionContext extends ParserRuleContext {
		public SelfExpressionContext selfExpression() {
			return getRuleContext(SelfExpressionContext.class,0);
		}
		public UnaryExpressionContext unaryExpression() {
			return getRuleContext(UnaryExpressionContext.class,0);
		}
		public TerminalNode AddAdd() { return getToken(MxstarParser.AddAdd, 0); }
		public TerminalNode SubSub() { return getToken(MxstarParser.SubSub, 0); }
		public TerminalNode Not() { return getToken(MxstarParser.Not, 0); }
		public TerminalNode Tilde() { return getToken(MxstarParser.Tilde, 0); }
		public TerminalNode Sub() { return getToken(MxstarParser.Sub, 0); }
		public TerminalNode Add() { return getToken(MxstarParser.Add, 0); }
		public UnaryExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterUnaryExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitUnaryExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitUnaryExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnaryExpressionContext unaryExpression() throws RecognitionException {
		UnaryExpressionContext _localctx = new UnaryExpressionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_unaryExpression);
		int _la;
		try {
			setState(183);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LParen:
			case LBrack:
			case New:
			case Null:
			case True:
			case False:
			case This:
			case Identifier:
			case IntLiteral:
			case StringLiteral:
				enterOuterAlt(_localctx, 1);
				{
				setState(180);
				selfExpression();
				}
				break;
			case Add:
			case Sub:
			case Not:
			case Tilde:
			case AddAdd:
			case SubSub:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(181);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Add) | (1L << Sub) | (1L << Not) | (1L << Tilde) | (1L << AddAdd) | (1L << SubSub))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(182);
				unaryExpression();
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MultiplicativeExpressionContext extends ParserRuleContext {
		public List<UnaryExpressionContext> unaryExpression() {
			return getRuleContexts(UnaryExpressionContext.class);
		}
		public UnaryExpressionContext unaryExpression(int i) {
			return getRuleContext(UnaryExpressionContext.class,i);
		}
		public List<TerminalNode> Mul() { return getTokens(MxstarParser.Mul); }
		public TerminalNode Mul(int i) {
			return getToken(MxstarParser.Mul, i);
		}
		public List<TerminalNode> Div() { return getTokens(MxstarParser.Div); }
		public TerminalNode Div(int i) {
			return getToken(MxstarParser.Div, i);
		}
		public List<TerminalNode> Mod() { return getTokens(MxstarParser.Mod); }
		public TerminalNode Mod(int i) {
			return getToken(MxstarParser.Mod, i);
		}
		public MultiplicativeExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplicativeExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterMultiplicativeExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitMultiplicativeExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitMultiplicativeExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultiplicativeExpressionContext multiplicativeExpression() throws RecognitionException {
		MultiplicativeExpressionContext _localctx = new MultiplicativeExpressionContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_multiplicativeExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(185);
			unaryExpression();
			setState(190);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Mul) | (1L << Div) | (1L << Mod))) != 0)) {
				{
				{
				setState(186);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Mul) | (1L << Div) | (1L << Mod))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(187);
				unaryExpression();
				}
				}
				setState(192);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AdditiveExpressionContext extends ParserRuleContext {
		public List<MultiplicativeExpressionContext> multiplicativeExpression() {
			return getRuleContexts(MultiplicativeExpressionContext.class);
		}
		public MultiplicativeExpressionContext multiplicativeExpression(int i) {
			return getRuleContext(MultiplicativeExpressionContext.class,i);
		}
		public List<TerminalNode> Add() { return getTokens(MxstarParser.Add); }
		public TerminalNode Add(int i) {
			return getToken(MxstarParser.Add, i);
		}
		public List<TerminalNode> Sub() { return getTokens(MxstarParser.Sub); }
		public TerminalNode Sub(int i) {
			return getToken(MxstarParser.Sub, i);
		}
		public AdditiveExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_additiveExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterAdditiveExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitAdditiveExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitAdditiveExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AdditiveExpressionContext additiveExpression() throws RecognitionException {
		AdditiveExpressionContext _localctx = new AdditiveExpressionContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_additiveExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(193);
			multiplicativeExpression();
			setState(198);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Add || _la==Sub) {
				{
				{
				setState(194);
				_la = _input.LA(1);
				if ( !(_la==Add || _la==Sub) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(195);
				multiplicativeExpression();
				}
				}
				setState(200);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ShiftExpressionContext extends ParserRuleContext {
		public List<AdditiveExpressionContext> additiveExpression() {
			return getRuleContexts(AdditiveExpressionContext.class);
		}
		public AdditiveExpressionContext additiveExpression(int i) {
			return getRuleContext(AdditiveExpressionContext.class,i);
		}
		public List<TerminalNode> LShift() { return getTokens(MxstarParser.LShift); }
		public TerminalNode LShift(int i) {
			return getToken(MxstarParser.LShift, i);
		}
		public List<TerminalNode> Rshift() { return getTokens(MxstarParser.Rshift); }
		public TerminalNode Rshift(int i) {
			return getToken(MxstarParser.Rshift, i);
		}
		public ShiftExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shiftExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterShiftExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitShiftExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitShiftExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ShiftExpressionContext shiftExpression() throws RecognitionException {
		ShiftExpressionContext _localctx = new ShiftExpressionContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_shiftExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(201);
			additiveExpression();
			setState(206);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LShift || _la==Rshift) {
				{
				{
				setState(202);
				_la = _input.LA(1);
				if ( !(_la==LShift || _la==Rshift) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(203);
				additiveExpression();
				}
				}
				setState(208);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RelationalExpressionContext extends ParserRuleContext {
		public List<ShiftExpressionContext> shiftExpression() {
			return getRuleContexts(ShiftExpressionContext.class);
		}
		public ShiftExpressionContext shiftExpression(int i) {
			return getRuleContext(ShiftExpressionContext.class,i);
		}
		public List<TerminalNode> Less() { return getTokens(MxstarParser.Less); }
		public TerminalNode Less(int i) {
			return getToken(MxstarParser.Less, i);
		}
		public List<TerminalNode> Greater() { return getTokens(MxstarParser.Greater); }
		public TerminalNode Greater(int i) {
			return getToken(MxstarParser.Greater, i);
		}
		public List<TerminalNode> LessEqual() { return getTokens(MxstarParser.LessEqual); }
		public TerminalNode LessEqual(int i) {
			return getToken(MxstarParser.LessEqual, i);
		}
		public List<TerminalNode> GreaterEqual() { return getTokens(MxstarParser.GreaterEqual); }
		public TerminalNode GreaterEqual(int i) {
			return getToken(MxstarParser.GreaterEqual, i);
		}
		public RelationalExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationalExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterRelationalExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitRelationalExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitRelationalExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelationalExpressionContext relationalExpression() throws RecognitionException {
		RelationalExpressionContext _localctx = new RelationalExpressionContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_relationalExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(209);
			shiftExpression();
			setState(214);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Less) | (1L << LessEqual) | (1L << Greater) | (1L << GreaterEqual))) != 0)) {
				{
				{
				setState(210);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Less) | (1L << LessEqual) | (1L << Greater) | (1L << GreaterEqual))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(211);
				shiftExpression();
				}
				}
				setState(216);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EqualityExpressionContext extends ParserRuleContext {
		public List<RelationalExpressionContext> relationalExpression() {
			return getRuleContexts(RelationalExpressionContext.class);
		}
		public RelationalExpressionContext relationalExpression(int i) {
			return getRuleContext(RelationalExpressionContext.class,i);
		}
		public List<TerminalNode> EqualEqual() { return getTokens(MxstarParser.EqualEqual); }
		public TerminalNode EqualEqual(int i) {
			return getToken(MxstarParser.EqualEqual, i);
		}
		public List<TerminalNode> NotEqual() { return getTokens(MxstarParser.NotEqual); }
		public TerminalNode NotEqual(int i) {
			return getToken(MxstarParser.NotEqual, i);
		}
		public EqualityExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_equalityExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterEqualityExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitEqualityExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitEqualityExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EqualityExpressionContext equalityExpression() throws RecognitionException {
		EqualityExpressionContext _localctx = new EqualityExpressionContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_equalityExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(217);
			relationalExpression();
			setState(222);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==EqualEqual || _la==NotEqual) {
				{
				{
				setState(218);
				_la = _input.LA(1);
				if ( !(_la==EqualEqual || _la==NotEqual) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(219);
				relationalExpression();
				}
				}
				setState(224);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AndExpressionContext extends ParserRuleContext {
		public List<EqualityExpressionContext> equalityExpression() {
			return getRuleContexts(EqualityExpressionContext.class);
		}
		public EqualityExpressionContext equalityExpression(int i) {
			return getRuleContext(EqualityExpressionContext.class,i);
		}
		public List<TerminalNode> And() { return getTokens(MxstarParser.And); }
		public TerminalNode And(int i) {
			return getToken(MxstarParser.And, i);
		}
		public AndExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_andExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterAndExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitAndExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitAndExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AndExpressionContext andExpression() throws RecognitionException {
		AndExpressionContext _localctx = new AndExpressionContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_andExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(225);
			equalityExpression();
			setState(230);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==And) {
				{
				{
				setState(226);
				match(And);
				setState(227);
				equalityExpression();
				}
				}
				setState(232);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExclusiveOrExpressionContext extends ParserRuleContext {
		public List<AndExpressionContext> andExpression() {
			return getRuleContexts(AndExpressionContext.class);
		}
		public AndExpressionContext andExpression(int i) {
			return getRuleContext(AndExpressionContext.class,i);
		}
		public List<TerminalNode> Caret() { return getTokens(MxstarParser.Caret); }
		public TerminalNode Caret(int i) {
			return getToken(MxstarParser.Caret, i);
		}
		public ExclusiveOrExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exclusiveOrExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterExclusiveOrExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitExclusiveOrExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitExclusiveOrExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExclusiveOrExpressionContext exclusiveOrExpression() throws RecognitionException {
		ExclusiveOrExpressionContext _localctx = new ExclusiveOrExpressionContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_exclusiveOrExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(233);
			andExpression();
			setState(238);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Caret) {
				{
				{
				setState(234);
				match(Caret);
				setState(235);
				andExpression();
				}
				}
				setState(240);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InclusiveOrExpressionContext extends ParserRuleContext {
		public List<ExclusiveOrExpressionContext> exclusiveOrExpression() {
			return getRuleContexts(ExclusiveOrExpressionContext.class);
		}
		public ExclusiveOrExpressionContext exclusiveOrExpression(int i) {
			return getRuleContext(ExclusiveOrExpressionContext.class,i);
		}
		public List<TerminalNode> Or() { return getTokens(MxstarParser.Or); }
		public TerminalNode Or(int i) {
			return getToken(MxstarParser.Or, i);
		}
		public InclusiveOrExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inclusiveOrExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterInclusiveOrExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitInclusiveOrExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitInclusiveOrExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InclusiveOrExpressionContext inclusiveOrExpression() throws RecognitionException {
		InclusiveOrExpressionContext _localctx = new InclusiveOrExpressionContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_inclusiveOrExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(241);
			exclusiveOrExpression();
			setState(246);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Or) {
				{
				{
				setState(242);
				match(Or);
				setState(243);
				exclusiveOrExpression();
				}
				}
				setState(248);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LogicalAndExpressionContext extends ParserRuleContext {
		public List<InclusiveOrExpressionContext> inclusiveOrExpression() {
			return getRuleContexts(InclusiveOrExpressionContext.class);
		}
		public InclusiveOrExpressionContext inclusiveOrExpression(int i) {
			return getRuleContext(InclusiveOrExpressionContext.class,i);
		}
		public List<TerminalNode> AndAnd() { return getTokens(MxstarParser.AndAnd); }
		public TerminalNode AndAnd(int i) {
			return getToken(MxstarParser.AndAnd, i);
		}
		public LogicalAndExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicalAndExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterLogicalAndExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitLogicalAndExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitLogicalAndExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LogicalAndExpressionContext logicalAndExpression() throws RecognitionException {
		LogicalAndExpressionContext _localctx = new LogicalAndExpressionContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_logicalAndExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(249);
			inclusiveOrExpression();
			setState(254);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AndAnd) {
				{
				{
				setState(250);
				match(AndAnd);
				setState(251);
				inclusiveOrExpression();
				}
				}
				setState(256);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LogicalOrExpressionContext extends ParserRuleContext {
		public List<LogicalAndExpressionContext> logicalAndExpression() {
			return getRuleContexts(LogicalAndExpressionContext.class);
		}
		public LogicalAndExpressionContext logicalAndExpression(int i) {
			return getRuleContext(LogicalAndExpressionContext.class,i);
		}
		public List<TerminalNode> OrOr() { return getTokens(MxstarParser.OrOr); }
		public TerminalNode OrOr(int i) {
			return getToken(MxstarParser.OrOr, i);
		}
		public LogicalOrExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicalOrExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterLogicalOrExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitLogicalOrExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitLogicalOrExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LogicalOrExpressionContext logicalOrExpression() throws RecognitionException {
		LogicalOrExpressionContext _localctx = new LogicalOrExpressionContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_logicalOrExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(257);
			logicalAndExpression();
			setState(262);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OrOr) {
				{
				{
				setState(258);
				match(OrOr);
				setState(259);
				logicalAndExpression();
				}
				}
				setState(264);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignmentExpressionContext extends ParserRuleContext {
		public List<LogicalOrExpressionContext> logicalOrExpression() {
			return getRuleContexts(LogicalOrExpressionContext.class);
		}
		public LogicalOrExpressionContext logicalOrExpression(int i) {
			return getRuleContext(LogicalOrExpressionContext.class,i);
		}
		public TerminalNode Assign() { return getToken(MxstarParser.Assign, 0); }
		public AssignmentExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignmentExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterAssignmentExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitAssignmentExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitAssignmentExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentExpressionContext assignmentExpression() throws RecognitionException {
		AssignmentExpressionContext _localctx = new AssignmentExpressionContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_assignmentExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(265);
			logicalOrExpression();
			setState(268);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Assign) {
				{
				setState(266);
				match(Assign);
				setState(267);
				logicalOrExpression();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public AssignmentExpressionContext assignmentExpression() {
			return getRuleContext(AssignmentExpressionContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(270);
			assignmentExpression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public VarDeclarationContext varDeclaration() {
			return getRuleContext(VarDeclarationContext.class,0);
		}
		public ExpressionStatementContext expressionStatement() {
			return getRuleContext(ExpressionStatementContext.class,0);
		}
		public CompoundStatementContext compoundStatement() {
			return getRuleContext(CompoundStatementContext.class,0);
		}
		public SelectionStatementContext selectionStatement() {
			return getRuleContext(SelectionStatementContext.class,0);
		}
		public IterationStatementContext iterationStatement() {
			return getRuleContext(IterationStatementContext.class,0);
		}
		public JumpStatementContext jumpStatement() {
			return getRuleContext(JumpStatementContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_statement);
		try {
			setState(278);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(272);
				varDeclaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(273);
				expressionStatement();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(274);
				compoundStatement();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(275);
				selectionStatement();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(276);
				iterationStatement();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(277);
				jumpStatement();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionStatementContext extends ParserRuleContext {
		public TerminalNode Semi() { return getToken(MxstarParser.Semi, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExpressionStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterExpressionStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitExpressionStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitExpressionStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionStatementContext expressionStatement() throws RecognitionException {
		ExpressionStatementContext _localctx = new ExpressionStatementContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_expressionStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(281);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Add) | (1L << Sub) | (1L << Not) | (1L << Tilde) | (1L << AddAdd) | (1L << SubSub) | (1L << LParen) | (1L << LBrack) | (1L << New) | (1L << Null) | (1L << True) | (1L << False) | (1L << This) | (1L << Identifier) | (1L << IntLiteral) | (1L << StringLiteral))) != 0)) {
				{
				setState(280);
				expression();
				}
			}

			setState(283);
			match(Semi);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CompoundStatementContext extends ParserRuleContext {
		public TerminalNode LBrace() { return getToken(MxstarParser.LBrace, 0); }
		public TerminalNode RBrace() { return getToken(MxstarParser.RBrace, 0); }
		public StatementSeqContext statementSeq() {
			return getRuleContext(StatementSeqContext.class,0);
		}
		public CompoundStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compoundStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterCompoundStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitCompoundStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitCompoundStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CompoundStatementContext compoundStatement() throws RecognitionException {
		CompoundStatementContext _localctx = new CompoundStatementContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_compoundStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(285);
			match(LBrace);
			setState(287);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Add) | (1L << Sub) | (1L << Not) | (1L << Tilde) | (1L << AddAdd) | (1L << SubSub) | (1L << LParen) | (1L << LBrack) | (1L << LBrace) | (1L << Semi) | (1L << Bool) | (1L << Int) | (1L << String) | (1L << New) | (1L << Null) | (1L << True) | (1L << False) | (1L << This) | (1L << If) | (1L << For) | (1L << While) | (1L << Break) | (1L << Continue) | (1L << Return) | (1L << Identifier) | (1L << IntLiteral) | (1L << StringLiteral))) != 0)) {
				{
				setState(286);
				statementSeq();
				}
			}

			setState(289);
			match(RBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementSeqContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public StatementSeqContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statementSeq; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterStatementSeq(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitStatementSeq(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitStatementSeq(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementSeqContext statementSeq() throws RecognitionException {
		StatementSeqContext _localctx = new StatementSeqContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_statementSeq);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(292); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(291);
				statement();
				}
				}
				setState(294); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Add) | (1L << Sub) | (1L << Not) | (1L << Tilde) | (1L << AddAdd) | (1L << SubSub) | (1L << LParen) | (1L << LBrack) | (1L << LBrace) | (1L << Semi) | (1L << Bool) | (1L << Int) | (1L << String) | (1L << New) | (1L << Null) | (1L << True) | (1L << False) | (1L << This) | (1L << If) | (1L << For) | (1L << While) | (1L << Break) | (1L << Continue) | (1L << Return) | (1L << Identifier) | (1L << IntLiteral) | (1L << StringLiteral))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SelectionStatementContext extends ParserRuleContext {
		public TerminalNode If() { return getToken(MxstarParser.If, 0); }
		public TerminalNode LParen() { return getToken(MxstarParser.LParen, 0); }
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public TerminalNode RParen() { return getToken(MxstarParser.RParen, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode Else() { return getToken(MxstarParser.Else, 0); }
		public SelectionStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectionStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterSelectionStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitSelectionStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitSelectionStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelectionStatementContext selectionStatement() throws RecognitionException {
		SelectionStatementContext _localctx = new SelectionStatementContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_selectionStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(296);
			match(If);
			setState(297);
			match(LParen);
			setState(298);
			condition();
			setState(299);
			match(RParen);
			setState(300);
			statement();
			setState(303);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
			case 1:
				{
				setState(301);
				match(Else);
				setState(302);
				statement();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConditionContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitCondition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(305);
			expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IterationStatementContext extends ParserRuleContext {
		public TerminalNode While() { return getToken(MxstarParser.While, 0); }
		public TerminalNode LParen() { return getToken(MxstarParser.LParen, 0); }
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public TerminalNode RParen() { return getToken(MxstarParser.RParen, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public TerminalNode For() { return getToken(MxstarParser.For, 0); }
		public ForInitStatementContext forInitStatement() {
			return getRuleContext(ForInitStatementContext.class,0);
		}
		public TerminalNode Semi() { return getToken(MxstarParser.Semi, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public IterationStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_iterationStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterIterationStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitIterationStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitIterationStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IterationStatementContext iterationStatement() throws RecognitionException {
		IterationStatementContext _localctx = new IterationStatementContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_iterationStatement);
		int _la;
		try {
			setState(326);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case While:
				enterOuterAlt(_localctx, 1);
				{
				setState(307);
				match(While);
				setState(308);
				match(LParen);
				setState(309);
				condition();
				setState(310);
				match(RParen);
				setState(311);
				statement();
				}
				break;
			case For:
				enterOuterAlt(_localctx, 2);
				{
				setState(313);
				match(For);
				setState(314);
				match(LParen);
				{
				setState(315);
				forInitStatement();
				setState(317);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Add) | (1L << Sub) | (1L << Not) | (1L << Tilde) | (1L << AddAdd) | (1L << SubSub) | (1L << LParen) | (1L << LBrack) | (1L << New) | (1L << Null) | (1L << True) | (1L << False) | (1L << This) | (1L << Identifier) | (1L << IntLiteral) | (1L << StringLiteral))) != 0)) {
					{
					setState(316);
					condition();
					}
				}

				setState(319);
				match(Semi);
				setState(321);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Add) | (1L << Sub) | (1L << Not) | (1L << Tilde) | (1L << AddAdd) | (1L << SubSub) | (1L << LParen) | (1L << LBrack) | (1L << New) | (1L << Null) | (1L << True) | (1L << False) | (1L << This) | (1L << Identifier) | (1L << IntLiteral) | (1L << StringLiteral))) != 0)) {
					{
					setState(320);
					expression();
					}
				}

				}
				setState(323);
				match(RParen);
				setState(324);
				statement();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForInitStatementContext extends ParserRuleContext {
		public ExpressionStatementContext expressionStatement() {
			return getRuleContext(ExpressionStatementContext.class,0);
		}
		public VarDeclarationContext varDeclaration() {
			return getRuleContext(VarDeclarationContext.class,0);
		}
		public ForInitStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forInitStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterForInitStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitForInitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitForInitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForInitStatementContext forInitStatement() throws RecognitionException {
		ForInitStatementContext _localctx = new ForInitStatementContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_forInitStatement);
		try {
			setState(330);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(328);
				expressionStatement();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(329);
				varDeclaration();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class JumpStatementContext extends ParserRuleContext {
		public TerminalNode Semi() { return getToken(MxstarParser.Semi, 0); }
		public TerminalNode Break() { return getToken(MxstarParser.Break, 0); }
		public TerminalNode Continue() { return getToken(MxstarParser.Continue, 0); }
		public TerminalNode Return() { return getToken(MxstarParser.Return, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public JumpStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jumpStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterJumpStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitJumpStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitJumpStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JumpStatementContext jumpStatement() throws RecognitionException {
		JumpStatementContext _localctx = new JumpStatementContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_jumpStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(338);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Break:
				{
				setState(332);
				match(Break);
				}
				break;
			case Continue:
				{
				setState(333);
				match(Continue);
				}
				break;
			case Return:
				{
				setState(334);
				match(Return);
				setState(336);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Add) | (1L << Sub) | (1L << Not) | (1L << Tilde) | (1L << AddAdd) | (1L << SubSub) | (1L << LParen) | (1L << LBrack) | (1L << New) | (1L << Null) | (1L << True) | (1L << False) | (1L << This) | (1L << Identifier) | (1L << IntLiteral) | (1L << StringLiteral))) != 0)) {
					{
					setState(335);
					expression();
					}
				}

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(340);
			match(Semi);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarDeclarationContext extends ParserRuleContext {
		public TypenameContext typename() {
			return getRuleContext(TypenameContext.class,0);
		}
		public List<DeclarationElementContext> declarationElement() {
			return getRuleContexts(DeclarationElementContext.class);
		}
		public DeclarationElementContext declarationElement(int i) {
			return getRuleContext(DeclarationElementContext.class,i);
		}
		public TerminalNode Semi() { return getToken(MxstarParser.Semi, 0); }
		public List<TerminalNode> Comma() { return getTokens(MxstarParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(MxstarParser.Comma, i);
		}
		public VarDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterVarDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitVarDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitVarDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDeclarationContext varDeclaration() throws RecognitionException {
		VarDeclarationContext _localctx = new VarDeclarationContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_varDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(342);
			typename();
			setState(343);
			declarationElement();
			setState(348);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(344);
				match(Comma);
				setState(345);
				declarationElement();
				}
				}
				setState(350);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(351);
			match(Semi);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclarationElementContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(MxstarParser.Identifier, 0); }
		public TerminalNode Assign() { return getToken(MxstarParser.Assign, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public DeclarationElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declarationElement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterDeclarationElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitDeclarationElement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitDeclarationElement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclarationElementContext declarationElement() throws RecognitionException {
		DeclarationElementContext _localctx = new DeclarationElementContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_declarationElement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(353);
			match(Identifier);
			setState(356);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Assign) {
				{
				setState(354);
				match(Assign);
				setState(355);
				expression();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncDefinitionContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(MxstarParser.Identifier, 0); }
		public FuncParameterContext funcParameter() {
			return getRuleContext(FuncParameterContext.class,0);
		}
		public CompoundStatementContext compoundStatement() {
			return getRuleContext(CompoundStatementContext.class,0);
		}
		public TypenameContext typename() {
			return getRuleContext(TypenameContext.class,0);
		}
		public TerminalNode Void() { return getToken(MxstarParser.Void, 0); }
		public FuncDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcDefinition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterFuncDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitFuncDefinition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitFuncDefinition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncDefinitionContext funcDefinition() throws RecognitionException {
		FuncDefinitionContext _localctx = new FuncDefinitionContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_funcDefinition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(360);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Bool:
			case Int:
			case String:
			case Identifier:
				{
				setState(358);
				typename();
				}
				break;
			case Void:
				{
				setState(359);
				match(Void);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(362);
			match(Identifier);
			setState(363);
			funcParameter();
			setState(364);
			compoundStatement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncParameterContext extends ParserRuleContext {
		public TerminalNode LParen() { return getToken(MxstarParser.LParen, 0); }
		public TerminalNode RParen() { return getToken(MxstarParser.RParen, 0); }
		public FuncParameterListContext funcParameterList() {
			return getRuleContext(FuncParameterListContext.class,0);
		}
		public FuncParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcParameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterFuncParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitFuncParameter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitFuncParameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncParameterContext funcParameter() throws RecognitionException {
		FuncParameterContext _localctx = new FuncParameterContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_funcParameter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(366);
			match(LParen);
			setState(368);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Bool) | (1L << Int) | (1L << String) | (1L << Identifier))) != 0)) {
				{
				setState(367);
				funcParameterList();
				}
			}

			setState(370);
			match(RParen);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncParameterListContext extends ParserRuleContext {
		public List<TypenameContext> typename() {
			return getRuleContexts(TypenameContext.class);
		}
		public TypenameContext typename(int i) {
			return getRuleContext(TypenameContext.class,i);
		}
		public List<TerminalNode> Identifier() { return getTokens(MxstarParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(MxstarParser.Identifier, i);
		}
		public List<TerminalNode> Comma() { return getTokens(MxstarParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(MxstarParser.Comma, i);
		}
		public FuncParameterListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcParameterList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterFuncParameterList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitFuncParameterList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitFuncParameterList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncParameterListContext funcParameterList() throws RecognitionException {
		FuncParameterListContext _localctx = new FuncParameterListContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_funcParameterList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(372);
			typename();
			setState(373);
			match(Identifier);
			setState(380);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(374);
				match(Comma);
				setState(375);
				typename();
				setState(376);
				match(Identifier);
				}
				}
				setState(382);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassDefinitionContext extends ParserRuleContext {
		public TerminalNode Class() { return getToken(MxstarParser.Class, 0); }
		public TerminalNode Identifier() { return getToken(MxstarParser.Identifier, 0); }
		public TerminalNode LBrace() { return getToken(MxstarParser.LBrace, 0); }
		public TerminalNode RBrace() { return getToken(MxstarParser.RBrace, 0); }
		public TerminalNode Semi() { return getToken(MxstarParser.Semi, 0); }
		public List<VarDeclarationContext> varDeclaration() {
			return getRuleContexts(VarDeclarationContext.class);
		}
		public VarDeclarationContext varDeclaration(int i) {
			return getRuleContext(VarDeclarationContext.class,i);
		}
		public List<ClassConstructionContext> classConstruction() {
			return getRuleContexts(ClassConstructionContext.class);
		}
		public ClassConstructionContext classConstruction(int i) {
			return getRuleContext(ClassConstructionContext.class,i);
		}
		public List<FuncDefinitionContext> funcDefinition() {
			return getRuleContexts(FuncDefinitionContext.class);
		}
		public FuncDefinitionContext funcDefinition(int i) {
			return getRuleContext(FuncDefinitionContext.class,i);
		}
		public ClassDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDefinition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterClassDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitClassDefinition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitClassDefinition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassDefinitionContext classDefinition() throws RecognitionException {
		ClassDefinitionContext _localctx = new ClassDefinitionContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_classDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(383);
			match(Class);
			setState(384);
			match(Identifier);
			setState(385);
			match(LBrace);
			setState(391);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Void) | (1L << Bool) | (1L << Int) | (1L << String) | (1L << Identifier))) != 0)) {
				{
				setState(389);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
				case 1:
					{
					setState(386);
					varDeclaration();
					}
					break;
				case 2:
					{
					setState(387);
					classConstruction();
					}
					break;
				case 3:
					{
					setState(388);
					funcDefinition();
					}
					break;
				}
				}
				setState(393);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(394);
			match(RBrace);
			setState(395);
			match(Semi);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassConstructionContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(MxstarParser.Identifier, 0); }
		public TerminalNode LParen() { return getToken(MxstarParser.LParen, 0); }
		public TerminalNode RParen() { return getToken(MxstarParser.RParen, 0); }
		public CompoundStatementContext compoundStatement() {
			return getRuleContext(CompoundStatementContext.class,0);
		}
		public ClassConstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classConstruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterClassConstruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitClassConstruction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitClassConstruction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassConstructionContext classConstruction() throws RecognitionException {
		ClassConstructionContext _localctx = new ClassConstructionContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_classConstruction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(397);
			match(Identifier);
			setState(398);
			match(LParen);
			setState(399);
			match(RParen);
			setState(400);
			compoundStatement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypenameContext extends ParserRuleContext {
		public TerminalNode Int() { return getToken(MxstarParser.Int, 0); }
		public TerminalNode Bool() { return getToken(MxstarParser.Bool, 0); }
		public TerminalNode String() { return getToken(MxstarParser.String, 0); }
		public TerminalNode Identifier() { return getToken(MxstarParser.Identifier, 0); }
		public ArrayParameterContext arrayParameter() {
			return getRuleContext(ArrayParameterContext.class,0);
		}
		public TypenameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typename; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterTypename(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitTypename(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitTypename(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypenameContext typename() throws RecognitionException {
		TypenameContext _localctx = new TypenameContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_typename);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(402);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Bool) | (1L << Int) | (1L << String) | (1L << Identifier))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(404);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,44,_ctx) ) {
			case 1:
				{
				setState(403);
				arrayParameter();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001<\u0197\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007\"\u0002"+
		"#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0002\'\u0007\'\u0002"+
		"(\u0007(\u0001\u0000\u0001\u0000\u0001\u0000\u0005\u0000V\b\u0000\n\u0000"+
		"\f\u0000Y\t\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0005"+
		"\u0000_\b\u0000\n\u0000\f\u0000b\t\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003"+
		"\u0002r\b\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0003\u0002{\b\u0002\u0001\u0003\u0001"+
		"\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0005\u0004\u0083"+
		"\b\u0004\n\u0004\f\u0004\u0086\t\u0004\u0003\u0004\u0088\b\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0003\u0005\u008e\b\u0005\u0001"+
		"\u0005\u0004\u0005\u0091\b\u0005\u000b\u0005\f\u0005\u0092\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0003\u0007\u009d\b\u0007\u0001\b\u0001\b\u0001\b\u0001\b"+
		"\u0001\b\u0001\b\u0001\t\u0001\t\u0003\t\u00a7\b\t\u0001\t\u0001\t\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003\n\u00b1\b\n\u0003\n\u00b3"+
		"\b\n\u0001\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u00b8\b\u000b\u0001"+
		"\f\u0001\f\u0001\f\u0005\f\u00bd\b\f\n\f\f\f\u00c0\t\f\u0001\r\u0001\r"+
		"\u0001\r\u0005\r\u00c5\b\r\n\r\f\r\u00c8\t\r\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0005\u000e\u00cd\b\u000e\n\u000e\f\u000e\u00d0\t\u000e\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0005\u000f\u00d5\b\u000f\n\u000f\f\u000f\u00d8"+
		"\t\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0005\u0010\u00dd\b\u0010"+
		"\n\u0010\f\u0010\u00e0\t\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0005"+
		"\u0011\u00e5\b\u0011\n\u0011\f\u0011\u00e8\t\u0011\u0001\u0012\u0001\u0012"+
		"\u0001\u0012\u0005\u0012\u00ed\b\u0012\n\u0012\f\u0012\u00f0\t\u0012\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0005\u0013\u00f5\b\u0013\n\u0013\f\u0013"+
		"\u00f8\t\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0005\u0014\u00fd\b"+
		"\u0014\n\u0014\f\u0014\u0100\t\u0014\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0005\u0015\u0105\b\u0015\n\u0015\f\u0015\u0108\t\u0015\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0003\u0016\u010d\b\u0016\u0001\u0017\u0001\u0017\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0003"+
		"\u0018\u0117\b\u0018\u0001\u0019\u0003\u0019\u011a\b\u0019\u0001\u0019"+
		"\u0001\u0019\u0001\u001a\u0001\u001a\u0003\u001a\u0120\b\u001a\u0001\u001a"+
		"\u0001\u001a\u0001\u001b\u0004\u001b\u0125\b\u001b\u000b\u001b\f\u001b"+
		"\u0126\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001"+
		"\u001c\u0001\u001c\u0003\u001c\u0130\b\u001c\u0001\u001d\u0001\u001d\u0001"+
		"\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001"+
		"\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0003\u001e\u013e\b\u001e\u0001"+
		"\u001e\u0001\u001e\u0003\u001e\u0142\b\u001e\u0001\u001e\u0001\u001e\u0001"+
		"\u001e\u0003\u001e\u0147\b\u001e\u0001\u001f\u0001\u001f\u0003\u001f\u014b"+
		"\b\u001f\u0001 \u0001 \u0001 \u0001 \u0003 \u0151\b \u0003 \u0153\b \u0001"+
		" \u0001 \u0001!\u0001!\u0001!\u0001!\u0005!\u015b\b!\n!\f!\u015e\t!\u0001"+
		"!\u0001!\u0001\"\u0001\"\u0001\"\u0003\"\u0165\b\"\u0001#\u0001#\u0003"+
		"#\u0169\b#\u0001#\u0001#\u0001#\u0001#\u0001$\u0001$\u0003$\u0171\b$\u0001"+
		"$\u0001$\u0001%\u0001%\u0001%\u0001%\u0001%\u0001%\u0005%\u017b\b%\n%"+
		"\f%\u017e\t%\u0001&\u0001&\u0001&\u0001&\u0001&\u0001&\u0005&\u0186\b"+
		"&\n&\f&\u0189\t&\u0001&\u0001&\u0001&\u0001\'\u0001\'\u0001\'\u0001\'"+
		"\u0001\'\u0001(\u0001(\u0003(\u0195\b(\u0001(\u0000\u0000)\u0000\u0002"+
		"\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e"+
		" \"$&(*,.02468:<>@BDFHJLNP\u0000\b\u0002\u0000+,;<\u0004\u0000\u0002\u0003"+
		"\u000f\u000f\u0015\u0015\u0017\u0018\u0001\u0000\u0004\u0006\u0001\u0000"+
		"\u0002\u0003\u0001\u0000\u0010\u0011\u0001\u0000\u0007\n\u0001\u0000\u000b"+
		"\f\u0002\u0000%\'::\u01aa\u0000W\u0001\u0000\u0000\u0000\u0002e\u0001"+
		"\u0000\u0000\u0000\u0004z\u0001\u0000\u0000\u0000\u0006|\u0001\u0000\u0000"+
		"\u0000\b~\u0001\u0000\u0000\u0000\n\u0090\u0001\u0000\u0000\u0000\f\u0094"+
		"\u0001\u0000\u0000\u0000\u000e\u0098\u0001\u0000\u0000\u0000\u0010\u009e"+
		"\u0001\u0000\u0000\u0000\u0012\u00a4\u0001\u0000\u0000\u0000\u0014\u00b2"+
		"\u0001\u0000\u0000\u0000\u0016\u00b7\u0001\u0000\u0000\u0000\u0018\u00b9"+
		"\u0001\u0000\u0000\u0000\u001a\u00c1\u0001\u0000\u0000\u0000\u001c\u00c9"+
		"\u0001\u0000\u0000\u0000\u001e\u00d1\u0001\u0000\u0000\u0000 \u00d9\u0001"+
		"\u0000\u0000\u0000\"\u00e1\u0001\u0000\u0000\u0000$\u00e9\u0001\u0000"+
		"\u0000\u0000&\u00f1\u0001\u0000\u0000\u0000(\u00f9\u0001\u0000\u0000\u0000"+
		"*\u0101\u0001\u0000\u0000\u0000,\u0109\u0001\u0000\u0000\u0000.\u010e"+
		"\u0001\u0000\u0000\u00000\u0116\u0001\u0000\u0000\u00002\u0119\u0001\u0000"+
		"\u0000\u00004\u011d\u0001\u0000\u0000\u00006\u0124\u0001\u0000\u0000\u0000"+
		"8\u0128\u0001\u0000\u0000\u0000:\u0131\u0001\u0000\u0000\u0000<\u0146"+
		"\u0001\u0000\u0000\u0000>\u014a\u0001\u0000\u0000\u0000@\u0152\u0001\u0000"+
		"\u0000\u0000B\u0156\u0001\u0000\u0000\u0000D\u0161\u0001\u0000\u0000\u0000"+
		"F\u0168\u0001\u0000\u0000\u0000H\u016e\u0001\u0000\u0000\u0000J\u0174"+
		"\u0001\u0000\u0000\u0000L\u017f\u0001\u0000\u0000\u0000N\u018d\u0001\u0000"+
		"\u0000\u0000P\u0192\u0001\u0000\u0000\u0000RV\u0003B!\u0000SV\u0003L&"+
		"\u0000TV\u0003F#\u0000UR\u0001\u0000\u0000\u0000US\u0001\u0000\u0000\u0000"+
		"UT\u0001\u0000\u0000\u0000VY\u0001\u0000\u0000\u0000WU\u0001\u0000\u0000"+
		"\u0000WX\u0001\u0000\u0000\u0000XZ\u0001\u0000\u0000\u0000YW\u0001\u0000"+
		"\u0000\u0000Z`\u0003\u0002\u0001\u0000[_\u0003B!\u0000\\_\u0003L&\u0000"+
		"]_\u0003F#\u0000^[\u0001\u0000\u0000\u0000^\\\u0001\u0000\u0000\u0000"+
		"^]\u0001\u0000\u0000\u0000_b\u0001\u0000\u0000\u0000`^\u0001\u0000\u0000"+
		"\u0000`a\u0001\u0000\u0000\u0000ac\u0001\u0000\u0000\u0000b`\u0001\u0000"+
		"\u0000\u0000cd\u0005\u0000\u0000\u0001d\u0001\u0001\u0000\u0000\u0000"+
		"ef\u0005&\u0000\u0000fg\u0005\u0001\u0000\u0000gh\u0005\u001a\u0000\u0000"+
		"hi\u0005\u001b\u0000\u0000ij\u00034\u001a\u0000j\u0003\u0001\u0000\u0000"+
		"\u0000k{\u0003\u0006\u0003\u0000l{\u0005-\u0000\u0000m{\u0005*\u0000\u0000"+
		"nq\u0005:\u0000\u0000or\u0003\b\u0004\u0000pr\u0003\n\u0005\u0000qo\u0001"+
		"\u0000\u0000\u0000qp\u0001\u0000\u0000\u0000qr\u0001\u0000\u0000\u0000"+
		"r{\u0001\u0000\u0000\u0000st\u0005\u001a\u0000\u0000tu\u0003.\u0017\u0000"+
		"uv\u0005\u001b\u0000\u0000v{\u0001\u0000\u0000\u0000w{\u0003\u0010\b\u0000"+
		"x{\u0003\f\u0006\u0000y{\u0003\u000e\u0007\u0000zk\u0001\u0000\u0000\u0000"+
		"zl\u0001\u0000\u0000\u0000zm\u0001\u0000\u0000\u0000zn\u0001\u0000\u0000"+
		"\u0000zs\u0001\u0000\u0000\u0000zw\u0001\u0000\u0000\u0000zx\u0001\u0000"+
		"\u0000\u0000zy\u0001\u0000\u0000\u0000{\u0005\u0001\u0000\u0000\u0000"+
		"|}\u0007\u0000\u0000\u0000}\u0007\u0001\u0000\u0000\u0000~\u0087\u0005"+
		"\u001a\u0000\u0000\u007f\u0084\u0003.\u0017\u0000\u0080\u0081\u0005#\u0000"+
		"\u0000\u0081\u0083\u0003.\u0017\u0000\u0082\u0080\u0001\u0000\u0000\u0000"+
		"\u0083\u0086\u0001\u0000\u0000\u0000\u0084\u0082\u0001\u0000\u0000\u0000"+
		"\u0084\u0085\u0001\u0000\u0000\u0000\u0085\u0088\u0001\u0000\u0000\u0000"+
		"\u0086\u0084\u0001\u0000\u0000\u0000\u0087\u007f\u0001\u0000\u0000\u0000"+
		"\u0087\u0088\u0001\u0000\u0000\u0000\u0088\u0089\u0001\u0000\u0000\u0000"+
		"\u0089\u008a\u0005\u001b\u0000\u0000\u008a\t\u0001\u0000\u0000\u0000\u008b"+
		"\u008d\u0005\u001c\u0000\u0000\u008c\u008e\u0003.\u0017\u0000\u008d\u008c"+
		"\u0001\u0000\u0000\u0000\u008d\u008e\u0001\u0000\u0000\u0000\u008e\u008f"+
		"\u0001\u0000\u0000\u0000\u008f\u0091\u0005\u001d\u0000\u0000\u0090\u008b"+
		"\u0001\u0000\u0000\u0000\u0091\u0092\u0001\u0000\u0000\u0000\u0092\u0090"+
		"\u0001\u0000\u0000\u0000\u0092\u0093\u0001\u0000\u0000\u0000\u0093\u000b"+
		"\u0001\u0000\u0000\u0000\u0094\u0095\u0005(\u0000\u0000\u0095\u0096\u0003"+
		"P(\u0000\u0096\u0097\u0003\n\u0005\u0000\u0097\r\u0001\u0000\u0000\u0000"+
		"\u0098\u0099\u0005(\u0000\u0000\u0099\u009c\u0005:\u0000\u0000\u009a\u009b"+
		"\u0005\u001a\u0000\u0000\u009b\u009d\u0005\u001b\u0000\u0000\u009c\u009a"+
		"\u0001\u0000\u0000\u0000\u009c\u009d\u0001\u0000\u0000\u0000\u009d\u000f"+
		"\u0001\u0000\u0000\u0000\u009e\u009f\u0003\u0012\t\u0000\u009f\u00a0\u0003"+
		"H$\u0000\u00a0\u00a1\u00055\u0000\u0000\u00a1\u00a2\u00034\u001a\u0000"+
		"\u00a2\u00a3\u0003\b\u0004\u0000\u00a3\u0011\u0001\u0000\u0000\u0000\u00a4"+
		"\u00a6\u0005\u001c\u0000\u0000\u00a5\u00a7\u0005\u0012\u0000\u0000\u00a6"+
		"\u00a5\u0001\u0000\u0000\u0000\u00a6\u00a7\u0001\u0000\u0000\u0000\u00a7"+
		"\u00a8\u0001\u0000\u0000\u0000\u00a8\u00a9\u0005\u001d\u0000\u0000\u00a9"+
		"\u0013\u0001\u0000\u0000\u0000\u00aa\u00b3\u0003\u0004\u0002\u0000\u00ab"+
		"\u00b0\u0003\u0004\u0002\u0000\u00ac\u00b1\u0005\u0017\u0000\u0000\u00ad"+
		"\u00b1\u0005\u0018\u0000\u0000\u00ae\u00af\u0005\u0019\u0000\u0000\u00af"+
		"\u00b1\u0003\u0014\n\u0000\u00b0\u00ac\u0001\u0000\u0000\u0000\u00b0\u00ad"+
		"\u0001\u0000\u0000\u0000\u00b0\u00ae\u0001\u0000\u0000\u0000\u00b1\u00b3"+
		"\u0001\u0000\u0000\u0000\u00b2\u00aa\u0001\u0000\u0000\u0000\u00b2\u00ab"+
		"\u0001\u0000\u0000\u0000\u00b3\u0015\u0001\u0000\u0000\u0000\u00b4\u00b8"+
		"\u0003\u0014\n\u0000\u00b5\u00b6\u0007\u0001\u0000\u0000\u00b6\u00b8\u0003"+
		"\u0016\u000b\u0000\u00b7\u00b4\u0001\u0000\u0000\u0000\u00b7\u00b5\u0001"+
		"\u0000\u0000\u0000\u00b8\u0017\u0001\u0000\u0000\u0000\u00b9\u00be\u0003"+
		"\u0016\u000b\u0000\u00ba\u00bb\u0007\u0002\u0000\u0000\u00bb\u00bd\u0003"+
		"\u0016\u000b\u0000\u00bc\u00ba\u0001\u0000\u0000\u0000\u00bd\u00c0\u0001"+
		"\u0000\u0000\u0000\u00be\u00bc\u0001\u0000\u0000\u0000\u00be\u00bf\u0001"+
		"\u0000\u0000\u0000\u00bf\u0019\u0001\u0000\u0000\u0000\u00c0\u00be\u0001"+
		"\u0000\u0000\u0000\u00c1\u00c6\u0003\u0018\f\u0000\u00c2\u00c3\u0007\u0003"+
		"\u0000\u0000\u00c3\u00c5\u0003\u0018\f\u0000\u00c4\u00c2\u0001\u0000\u0000"+
		"\u0000\u00c5\u00c8\u0001\u0000\u0000\u0000\u00c6\u00c4\u0001\u0000\u0000"+
		"\u0000\u00c6\u00c7\u0001\u0000\u0000\u0000\u00c7\u001b\u0001\u0000\u0000"+
		"\u0000\u00c8\u00c6\u0001\u0000\u0000\u0000\u00c9\u00ce\u0003\u001a\r\u0000"+
		"\u00ca\u00cb\u0007\u0004\u0000\u0000\u00cb\u00cd\u0003\u001a\r\u0000\u00cc"+
		"\u00ca\u0001\u0000\u0000\u0000\u00cd\u00d0\u0001\u0000\u0000\u0000\u00ce"+
		"\u00cc\u0001\u0000\u0000\u0000\u00ce\u00cf\u0001\u0000\u0000\u0000\u00cf"+
		"\u001d\u0001\u0000\u0000\u0000\u00d0\u00ce\u0001\u0000\u0000\u0000\u00d1"+
		"\u00d6\u0003\u001c\u000e\u0000\u00d2\u00d3\u0007\u0005\u0000\u0000\u00d3"+
		"\u00d5\u0003\u001c\u000e\u0000\u00d4\u00d2\u0001\u0000\u0000\u0000\u00d5"+
		"\u00d8\u0001\u0000\u0000\u0000\u00d6\u00d4\u0001\u0000\u0000\u0000\u00d6"+
		"\u00d7\u0001\u0000\u0000\u0000\u00d7\u001f\u0001\u0000\u0000\u0000\u00d8"+
		"\u00d6\u0001\u0000\u0000\u0000\u00d9\u00de\u0003\u001e\u000f\u0000\u00da"+
		"\u00db\u0007\u0006\u0000\u0000\u00db\u00dd\u0003\u001e\u000f\u0000\u00dc"+
		"\u00da\u0001\u0000\u0000\u0000\u00dd\u00e0\u0001\u0000\u0000\u0000\u00de"+
		"\u00dc\u0001\u0000\u0000\u0000\u00de\u00df\u0001\u0000\u0000\u0000\u00df"+
		"!\u0001\u0000\u0000\u0000\u00e0\u00de\u0001\u0000\u0000\u0000\u00e1\u00e6"+
		"\u0003 \u0010\u0000\u00e2\u00e3\u0005\u0012\u0000\u0000\u00e3\u00e5\u0003"+
		" \u0010\u0000\u00e4\u00e2\u0001\u0000\u0000\u0000\u00e5\u00e8\u0001\u0000"+
		"\u0000\u0000\u00e6\u00e4\u0001\u0000\u0000\u0000\u00e6\u00e7\u0001\u0000"+
		"\u0000\u0000\u00e7#\u0001\u0000\u0000\u0000\u00e8\u00e6\u0001\u0000\u0000"+
		"\u0000\u00e9\u00ee\u0003\"\u0011\u0000\u00ea\u00eb\u0005\u0014\u0000\u0000"+
		"\u00eb\u00ed\u0003\"\u0011\u0000\u00ec\u00ea\u0001\u0000\u0000\u0000\u00ed"+
		"\u00f0\u0001\u0000\u0000\u0000\u00ee\u00ec\u0001\u0000\u0000\u0000\u00ee"+
		"\u00ef\u0001\u0000\u0000\u0000\u00ef%\u0001\u0000\u0000\u0000\u00f0\u00ee"+
		"\u0001\u0000\u0000\u0000\u00f1\u00f6\u0003$\u0012\u0000\u00f2\u00f3\u0005"+
		"\u0013\u0000\u0000\u00f3\u00f5\u0003$\u0012\u0000\u00f4\u00f2\u0001\u0000"+
		"\u0000\u0000\u00f5\u00f8\u0001\u0000\u0000\u0000\u00f6\u00f4\u0001\u0000"+
		"\u0000\u0000\u00f6\u00f7\u0001\u0000\u0000\u0000\u00f7\'\u0001\u0000\u0000"+
		"\u0000\u00f8\u00f6\u0001\u0000\u0000\u0000\u00f9\u00fe\u0003&\u0013\u0000"+
		"\u00fa\u00fb\u0005\r\u0000\u0000\u00fb\u00fd\u0003&\u0013\u0000\u00fc"+
		"\u00fa\u0001\u0000\u0000\u0000\u00fd\u0100\u0001\u0000\u0000\u0000\u00fe"+
		"\u00fc\u0001\u0000\u0000\u0000\u00fe\u00ff\u0001\u0000\u0000\u0000\u00ff"+
		")\u0001\u0000\u0000\u0000\u0100\u00fe\u0001\u0000\u0000\u0000\u0101\u0106"+
		"\u0003(\u0014\u0000\u0102\u0103\u0005\u000e\u0000\u0000\u0103\u0105\u0003"+
		"(\u0014\u0000\u0104\u0102\u0001\u0000\u0000\u0000\u0105\u0108\u0001\u0000"+
		"\u0000\u0000\u0106\u0104\u0001\u0000\u0000\u0000\u0106\u0107\u0001\u0000"+
		"\u0000\u0000\u0107+\u0001\u0000\u0000\u0000\u0108\u0106\u0001\u0000\u0000"+
		"\u0000\u0109\u010c\u0003*\u0015\u0000\u010a\u010b\u0005\u0016\u0000\u0000"+
		"\u010b\u010d\u0003*\u0015\u0000\u010c\u010a\u0001\u0000\u0000\u0000\u010c"+
		"\u010d\u0001\u0000\u0000\u0000\u010d-\u0001\u0000\u0000\u0000\u010e\u010f"+
		"\u0003,\u0016\u0000\u010f/\u0001\u0000\u0000\u0000\u0110\u0117\u0003B"+
		"!\u0000\u0111\u0117\u00032\u0019\u0000\u0112\u0117\u00034\u001a\u0000"+
		"\u0113\u0117\u00038\u001c\u0000\u0114\u0117\u0003<\u001e\u0000\u0115\u0117"+
		"\u0003@ \u0000\u0116\u0110\u0001\u0000\u0000\u0000\u0116\u0111\u0001\u0000"+
		"\u0000\u0000\u0116\u0112\u0001\u0000\u0000\u0000\u0116\u0113\u0001\u0000"+
		"\u0000\u0000\u0116\u0114\u0001\u0000\u0000\u0000\u0116\u0115\u0001\u0000"+
		"\u0000\u0000\u01171\u0001\u0000\u0000\u0000\u0118\u011a\u0003.\u0017\u0000"+
		"\u0119\u0118\u0001\u0000\u0000\u0000\u0119\u011a\u0001\u0000\u0000\u0000"+
		"\u011a\u011b\u0001\u0000\u0000\u0000\u011b\u011c\u0005\"\u0000\u0000\u011c"+
		"3\u0001\u0000\u0000\u0000\u011d\u011f\u0005\u001e\u0000\u0000\u011e\u0120"+
		"\u00036\u001b\u0000\u011f\u011e\u0001\u0000\u0000\u0000\u011f\u0120\u0001"+
		"\u0000\u0000\u0000\u0120\u0121\u0001\u0000\u0000\u0000\u0121\u0122\u0005"+
		"\u001f\u0000\u0000\u01225\u0001\u0000\u0000\u0000\u0123\u0125\u00030\u0018"+
		"\u0000\u0124\u0123\u0001\u0000\u0000\u0000\u0125\u0126\u0001\u0000\u0000"+
		"\u0000\u0126\u0124\u0001\u0000\u0000\u0000\u0126\u0127\u0001\u0000\u0000"+
		"\u0000\u01277\u0001\u0000\u0000\u0000\u0128\u0129\u0005.\u0000\u0000\u0129"+
		"\u012a\u0005\u001a\u0000\u0000\u012a\u012b\u0003:\u001d\u0000\u012b\u012c"+
		"\u0005\u001b\u0000\u0000\u012c\u012f\u00030\u0018\u0000\u012d\u012e\u0005"+
		"/\u0000\u0000\u012e\u0130\u00030\u0018\u0000\u012f\u012d\u0001\u0000\u0000"+
		"\u0000\u012f\u0130\u0001\u0000\u0000\u0000\u01309\u0001\u0000\u0000\u0000"+
		"\u0131\u0132\u0003.\u0017\u0000\u0132;\u0001\u0000\u0000\u0000\u0133\u0134"+
		"\u00051\u0000\u0000\u0134\u0135\u0005\u001a\u0000\u0000\u0135\u0136\u0003"+
		":\u001d\u0000\u0136\u0137\u0005\u001b\u0000\u0000\u0137\u0138\u00030\u0018"+
		"\u0000\u0138\u0147\u0001\u0000\u0000\u0000\u0139\u013a\u00050\u0000\u0000"+
		"\u013a\u013b\u0005\u001a\u0000\u0000\u013b\u013d\u0003>\u001f\u0000\u013c"+
		"\u013e\u0003:\u001d\u0000\u013d\u013c\u0001\u0000\u0000\u0000\u013d\u013e"+
		"\u0001\u0000\u0000\u0000\u013e\u013f\u0001\u0000\u0000\u0000\u013f\u0141"+
		"\u0005\"\u0000\u0000\u0140\u0142\u0003.\u0017\u0000\u0141\u0140\u0001"+
		"\u0000\u0000\u0000\u0141\u0142\u0001\u0000\u0000\u0000\u0142\u0143\u0001"+
		"\u0000\u0000\u0000\u0143\u0144\u0005\u001b\u0000\u0000\u0144\u0145\u0003"+
		"0\u0018\u0000\u0145\u0147\u0001\u0000\u0000\u0000\u0146\u0133\u0001\u0000"+
		"\u0000\u0000\u0146\u0139\u0001\u0000\u0000\u0000\u0147=\u0001\u0000\u0000"+
		"\u0000\u0148\u014b\u00032\u0019\u0000\u0149\u014b\u0003B!\u0000\u014a"+
		"\u0148\u0001\u0000\u0000\u0000\u014a\u0149\u0001\u0000\u0000\u0000\u014b"+
		"?\u0001\u0000\u0000\u0000\u014c\u0153\u00052\u0000\u0000\u014d\u0153\u0005"+
		"3\u0000\u0000\u014e\u0150\u00054\u0000\u0000\u014f\u0151\u0003.\u0017"+
		"\u0000\u0150\u014f\u0001\u0000\u0000\u0000\u0150\u0151\u0001\u0000\u0000"+
		"\u0000\u0151\u0153\u0001\u0000\u0000\u0000\u0152\u014c\u0001\u0000\u0000"+
		"\u0000\u0152\u014d\u0001\u0000\u0000\u0000\u0152\u014e\u0001\u0000\u0000"+
		"\u0000\u0153\u0154\u0001\u0000\u0000\u0000\u0154\u0155\u0005\"\u0000\u0000"+
		"\u0155A\u0001\u0000\u0000\u0000\u0156\u0157\u0003P(\u0000\u0157\u015c"+
		"\u0003D\"\u0000\u0158\u0159\u0005#\u0000\u0000\u0159\u015b\u0003D\"\u0000"+
		"\u015a\u0158\u0001\u0000\u0000\u0000\u015b\u015e\u0001\u0000\u0000\u0000"+
		"\u015c\u015a\u0001\u0000\u0000\u0000\u015c\u015d\u0001\u0000\u0000\u0000"+
		"\u015d\u015f\u0001\u0000\u0000\u0000\u015e\u015c\u0001\u0000\u0000\u0000"+
		"\u015f\u0160\u0005\"\u0000\u0000\u0160C\u0001\u0000\u0000\u0000\u0161"+
		"\u0164\u0005:\u0000\u0000\u0162\u0163\u0005\u0016\u0000\u0000\u0163\u0165"+
		"\u0003.\u0017\u0000\u0164\u0162\u0001\u0000\u0000\u0000\u0164\u0165\u0001"+
		"\u0000\u0000\u0000\u0165E\u0001\u0000\u0000\u0000\u0166\u0169\u0003P("+
		"\u0000\u0167\u0169\u0005$\u0000\u0000\u0168\u0166\u0001\u0000\u0000\u0000"+
		"\u0168\u0167\u0001\u0000\u0000\u0000\u0169\u016a\u0001\u0000\u0000\u0000"+
		"\u016a\u016b\u0005:\u0000\u0000\u016b\u016c\u0003H$\u0000\u016c\u016d"+
		"\u00034\u001a\u0000\u016dG\u0001\u0000\u0000\u0000\u016e\u0170\u0005\u001a"+
		"\u0000\u0000\u016f\u0171\u0003J%\u0000\u0170\u016f\u0001\u0000\u0000\u0000"+
		"\u0170\u0171\u0001\u0000\u0000\u0000\u0171\u0172\u0001\u0000\u0000\u0000"+
		"\u0172\u0173\u0005\u001b\u0000\u0000\u0173I\u0001\u0000\u0000\u0000\u0174"+
		"\u0175\u0003P(\u0000\u0175\u017c\u0005:\u0000\u0000\u0176\u0177\u0005"+
		"#\u0000\u0000\u0177\u0178\u0003P(\u0000\u0178\u0179\u0005:\u0000\u0000"+
		"\u0179\u017b\u0001\u0000\u0000\u0000\u017a\u0176\u0001\u0000\u0000\u0000"+
		"\u017b\u017e\u0001\u0000\u0000\u0000\u017c\u017a\u0001\u0000\u0000\u0000"+
		"\u017c\u017d\u0001\u0000\u0000\u0000\u017dK\u0001\u0000\u0000\u0000\u017e"+
		"\u017c\u0001\u0000\u0000\u0000\u017f\u0180\u0005)\u0000\u0000\u0180\u0181"+
		"\u0005:\u0000\u0000\u0181\u0187\u0005\u001e\u0000\u0000\u0182\u0186\u0003"+
		"B!\u0000\u0183\u0186\u0003N\'\u0000\u0184\u0186\u0003F#\u0000\u0185\u0182"+
		"\u0001\u0000\u0000\u0000\u0185\u0183\u0001\u0000\u0000\u0000\u0185\u0184"+
		"\u0001\u0000\u0000\u0000\u0186\u0189\u0001\u0000\u0000\u0000\u0187\u0185"+
		"\u0001\u0000\u0000\u0000\u0187\u0188\u0001\u0000\u0000\u0000\u0188\u018a"+
		"\u0001\u0000\u0000\u0000\u0189\u0187\u0001\u0000\u0000\u0000\u018a\u018b"+
		"\u0005\u001f\u0000\u0000\u018b\u018c\u0005\"\u0000\u0000\u018cM\u0001"+
		"\u0000\u0000\u0000\u018d\u018e\u0005:\u0000\u0000\u018e\u018f\u0005\u001a"+
		"\u0000\u0000\u018f\u0190\u0005\u001b\u0000\u0000\u0190\u0191\u00034\u001a"+
		"\u0000\u0191O\u0001\u0000\u0000\u0000\u0192\u0194\u0007\u0007\u0000\u0000"+
		"\u0193\u0195\u0003\n\u0005\u0000\u0194\u0193\u0001\u0000\u0000\u0000\u0194"+
		"\u0195\u0001\u0000\u0000\u0000\u0195Q\u0001\u0000\u0000\u0000-UW^`qz\u0084"+
		"\u0087\u008d\u0092\u009c\u00a6\u00b0\u00b2\u00b7\u00be\u00c6\u00ce\u00d6"+
		"\u00de\u00e6\u00ee\u00f6\u00fe\u0106\u010c\u0116\u0119\u011f\u0126\u012f"+
		"\u013d\u0141\u0146\u014a\u0150\u0152\u015c\u0164\u0168\u0170\u017c\u0185"+
		"\u0187\u0194";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}